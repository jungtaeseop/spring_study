package kitchenpos.application;

import kitchenpos.domain.*;
import kitchenpos.infra.KitchenridersClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final OrderTableRepository orderTableRepository;
    private final KitchenridersClient kitchenridersClient;

    public OrderService(
        final OrderRepository orderRepository,
        final MenuRepository menuRepository,
        final OrderTableRepository orderTableRepository,
        final KitchenridersClient kitchenridersClient
    ) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
        this.orderTableRepository = orderTableRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public Order create(final Order request) {
        /**
         * 주문 생성할 때 배달 주문인지 , 매장 주문인지, 포장 주문인지 3개 중에 한개는 필수 값으로 입력해야 한다.
         * */
        final OrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        /**
         * 주문 생성할 때 메뉴 목록을 필수 값으로 입력해야 한다.
         * */
        final List<OrderLineItem> orderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(orderLineItemRequests) || orderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        /**
         * 주문 생성할 때 메뉴 목록과 등록되어 있는 메뉴의 개수가 같아야 된다.
         * */
        final List<Menu> menus = menuRepository.findAllByIdIn(
            orderLineItemRequests.stream()
                .map(OrderLineItem::getMenuId)
                .collect(Collectors.toList())
        );
        if (menus.size() != orderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }

        final List<OrderLineItem> orderLineItems = new ArrayList<>();
        for (final OrderLineItem orderLineItemRequest : orderLineItemRequests) {

            /**
             * 주문 생성 시 배달이나 포장일 때 주문한 메뉴 개수가  0 이상이어야 한다.
             * */
            final long quantity = orderLineItemRequest.getQuantity();
            if (type != OrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }
            /**
             *  메뉴에 등록이 된 메뉴를 주문해야 된다.
             * */
            final Menu menu = menuRepository.findById(orderLineItemRequest.getMenuId())
                .orElseThrow(NoSuchElementException::new);
            /**
             * 전시가 되어 있는 메뉴를 주문 해야된다.
             * */
            if (!menu.isDisplayed()) {
                throw new IllegalStateException();
            }
            /**
             * 등록된 메뉴의 가격과 주문한 메뉴의 가격이 같아야 된다.
             * */
            if (menu.getPrice().compareTo(orderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException();
            }
            final OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setMenu(menu);
            orderLineItem.setQuantity(quantity);
            orderLineItems.add(orderLineItem);
        }
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setType(type);
        order.setStatus(OrderStatus.WAITING);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);
        /**
         * 배달 주문을 생성할 때는 반드시 배달지 주소가 필요합니다.
         * */
        if (type == OrderType.DELIVERY) {
            final String deliveryAddress = request.getDeliveryAddress();
            if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
                throw new IllegalArgumentException();
            }
            order.setDeliveryAddress(deliveryAddress);
        }
        /**
         * 매장에서 주문할 때 등록된 주문테이블 id가 반드시 있어야 한다.
         * */
        if (type == OrderType.EAT_IN) {
            final OrderTable orderTable = orderTableRepository.findById(request.getOrderTableId())
                .orElseThrow(NoSuchElementException::new);
            /**
             * 매장에서 주문할 때 주문테이블이 사용중이여야 한다.
             * */
            if (!orderTable.isOccupied()) {
                throw new IllegalStateException();
            }
            order.setOrderTable(orderTable);
        }
        return orderRepository.save(order);
    }

    @Transactional
    public Order accept(final UUID orderId) {
        /**
         * 주문 수락할 때 주문 id가 있어야 한다.
         * */
        final Order order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        /**
         * 주문 상태가 대기중(WAITING) 이어야 한다. 다른 값(ACCEPTED, SERVED, DELIVERING, DELIVERED, COMPLETED)
         * */
        if (order.getStatus() != OrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        /**
         * sum.add 필요할 것 같아서 수정
         * 배달 라이더한테 주문번호, 총 금액, 배달지 주소를 전달한다.
         * 주문 상태를 ACCEPTED(수락) 상태로 변경한다.
         * */
        if (order.getType() == OrderType.DELIVERY) {
            BigDecimal sum = BigDecimal.ZERO;
            for (final OrderLineItem orderLineItem : order.getOrderLineItems()) {
                sum = sum.add(orderLineItem.getMenu()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(orderLineItem.getQuantity())));
            }
            kitchenridersClient.requestDelivery(orderId, sum, order.getDeliveryAddress());
        }
        order.setStatus(OrderStatus.ACCEPTED);
        return order;
    }

    @Transactional
    public Order serve(final UUID orderId) {
        /**
         * 음식을 제공할 때 주문 id가 있어야 한다.
         * */
        final Order order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        /**
         * 주문 상태가 ACCEPTED(수락됨) 이면 주문 상태를 SERVED(제공됨) 로 변경한다.
         * */
        if (order.getStatus() != OrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        order.setStatus(OrderStatus.SERVED);
        return order;
    }

    @Transactional
    public Order startDelivery(final UUID orderId) {
        /**
         * 배달 시작할 때 주문번호가 있어야 한다.
         * */
        final Order order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        /**
         *  배달 시작할 때 주문타입이 배달 타입이어야 한다.
         * */
        if (order.getType() != OrderType.DELIVERY) {
            throw new IllegalStateException();
        }
        /**
         * 배달 시작할 때 주문상태가 음식이 배달기사한테 제공됨 상태여야 한다.
         * */
        if (order.getStatus() != OrderStatus.SERVED) {
            throw new IllegalStateException();
        }
        /**
         * 배달 시작할 때 주문상태를 배달중으로 변경한다.
         * */
        order.setStatus(OrderStatus.DELIVERING);
        return order;
    }

    @Transactional
    public Order completeDelivery(final UUID orderId) {
        /**
         * 배달이 완료됐을 때 주문번호가 있어야 한다.
         * */
        final Order order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        /**
         * 주문상태가 배달중이어야 한다.
         * */
        if (order.getStatus() != OrderStatus.DELIVERING) {
            throw new IllegalStateException();
        }
        /**
         * 주문상태가 배달 완료로 변경한다.
         * */
        order.setStatus(OrderStatus.DELIVERED);
        return order;
    }

    @Transactional
    public Order complete(final UUID orderId) {
        /**
         * 주문이 완료됐을 때 주문번호가 있어야 한다.
         * */
        final Order order = orderRepository.findById(orderId)
            .orElseThrow(NoSuchElementException::new);
        final OrderType type = order.getType();
        final OrderStatus status = order.getStatus();
        /**
         * 주문타입이 배달일 때 주문상태가 배달 완료여야 한다.
         * */
        if (type == OrderType.DELIVERY) {
            if (status != OrderStatus.DELIVERED) {
                throw new IllegalStateException();
            }
        }
        /**
         * 주문타입이 포장이거나 매장에서 식사할 때 주문의 음식 상태가 제공됨이어야 한다.
         * */
        if (type == OrderType.TAKEOUT || type == OrderType.EAT_IN) {
            if (status != OrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        /**
         * 주문상태를 완료로 변경한다.
         * */
        order.setStatus(OrderStatus.COMPLETED);
        /**
         * 주문타입이 매장에서 식사일 때 주문상태가 완료이고 주문테이블이 사용중이 아니면,
         * 주문테이블 인원수를 0변경하고 사용중이 아닌것으로 변경한다.
         * */
        if (type == OrderType.EAT_IN) {
            final OrderTable orderTable = order.getOrderTable();
            if (!orderRepository.existsByOrderTableAndStatusNot(orderTable, OrderStatus.COMPLETED)) {
                orderTable.setNumberOfGuests(0);
                orderTable.setOccupied(false);
            }
        }
        return order;
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
