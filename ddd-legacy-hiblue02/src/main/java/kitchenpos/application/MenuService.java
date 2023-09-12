package kitchenpos.application;

import kitchenpos.domain.*;
import kitchenpos.infra.PurgomalumClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final ProductRepository productRepository;
    private final PurgomalumClient purgomalumClient;

    public MenuService(
        final MenuRepository menuRepository,
        final MenuGroupRepository menuGroupRepository,
        final ProductRepository productRepository,
        final PurgomalumClient purgomalumClient
    ) {
        this.menuRepository = menuRepository;
        this.menuGroupRepository = menuGroupRepository;
        this.productRepository = productRepository;
        this.purgomalumClient = purgomalumClient;
    }

    @Transactional
    public Menu create(final Menu request) {
        final BigDecimal price = request.getPrice();
        /**
         *
         * price.compareTo 비교  :  price 와 BigDecimal.ZERO 같으면 0  , price가 작으면 -1
         * 메뉴 생성할 때  메뉴의 가격이 필수로 입력이 되어야 하고, 0 이상이어야한다.
         */
        if (Objects.isNull(price) || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        /**
         * 메뉴 생성할 때  메뉴에 미리 등록된 메뉴 그룹이 입력되어 있어야 한다.
         */
        final MenuGroup menuGroup = menuGroupRepository.findById(request.getMenuGroupId())
            .orElseThrow(NoSuchElementException::new);
        /**
         *
         * 메뉴 생성할 때 메뉴구성상품이 반드시 있어야된다.
         */
        final List<MenuProduct> menuProductRequests = request.getMenuProducts();
        if (Objects.isNull(menuProductRequests) || menuProductRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        /**
         *  메뉴 생성할 때  입력된 상품은 반드시 등록된 상품 목록에 포함되어 있어야 한다.
         *  (상품의 uuid 값이 없는 경우 넣을 경우 row수가 안맞음)
         *  select * from product where id IN(
         *     UNHEX(REPLACE('4721ee72-2ff3-417f-ade3-acd0a804605b','-', ''))
         *     ,UNHEX(REPLACE('5bfae32d-19bc-479f-8e08-8b8fee193452','-', ''))
         *     ); 쿼리를 입력했을때 uuid가 있는 값만 출력 됩니다. 없는 값을 넣어도 오류는 없음
         * */
        final List<Product> products = productRepository.findAllByIdIn(
            menuProductRequests.stream()
                .map(MenuProduct::getProductId)
                .collect(Collectors.toList())
        );

        if (products.size() != menuProductRequests.size()) {
            throw new IllegalArgumentException();
        }

        /**
         * List<MenuProduct> 값을 생성
         * BigDecimal 생성 및 초기화
         * */
        final List<MenuProduct> menuProducts = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO;

        /**
         * 메뉴 생성할 때 입력된 상품의 개수가 0 이상이어야 한다.
         * 메뉴 생성할 때 입력된 상품의 id가 반드시 있어야 하고 등록이 되어 있어야한다.
         *
         * 제품 가격(product.getPrice())을 특정 수량('quantity')으로 곱합니다  기존의 sum 값과 새로 계산된 곱한 값을  더한 후에 원래의 'sum' 변수에 할당
         * 수량만큼의 제품 가격을 계산하고 그 결과를 sum값 과 합쳐서 'sum' 변수에 저장
         * 메뉴구성상품 변수 선언 -> 값 세팅 상품,상품의 개수를 메뉴구성상품 엔티티 변수에 저장
         *
         * */
        for (final MenuProduct menuProductRequest : menuProductRequests) {
            final long quantity = menuProductRequest.getQuantity();
            if (quantity < 0) {
                throw new IllegalArgumentException();
            }
            final Product product = productRepository.findById(menuProductRequest.getProductId())
                .orElseThrow(NoSuchElementException::new);
            sum = sum.add(
                product.getPrice().multiply(BigDecimal.valueOf(quantity))
            );
            final MenuProduct menuProduct = new MenuProduct();
            menuProduct.setProduct(product);
            menuProduct.setQuantity(quantity);
            menuProducts.add(menuProduct);
        }
        /**
         * 메뉴 가격(price)이 상품과 개수 곱하고 List로 돌면서 합한 값(sum) 보다 작거나 같아야된다.
         * 상품의 더한 값이 가격이 더 높아야된다.
         * */
        if (price.compareTo(sum) > 0) {
            throw new IllegalArgumentException();
        }
        /**
         * 메뉴 이름 변수를 선언
         * 메뉴이름이 null 이거나 또는 메뉴이름에 욕설이 들어가면 안된다.
         * */
        final String name = request.getName();
        if (Objects.isNull(name) || purgomalumClient.containsProfanity(name)) {
            throw new IllegalArgumentException();
        }
        /**
         * 메뉴 엔티티 선언
         * 메뉴의 값들을 넣어 줘서  메뉴 값을 저장한다.
         * */
        final Menu menu = new Menu();
        menu.setId(UUID.randomUUID());
        menu.setName(name);
        menu.setPrice(price);
        menu.setMenuGroup(menuGroup);
        menu.setDisplayed(request.isDisplayed());
        menu.setMenuProducts(menuProducts);
        return menuRepository.save(menu);
    }

    @Transactional
    public Menu changePrice(final UUID menuId, final Menu request) {
        final BigDecimal price = request.getPrice();
        /**
         * 가격이 필수로 있어야 되고 0보다 커야 됩니다.
         * */
        if (Objects.isNull(price) || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        /**
         * 메뉴테이블에 등록된 메뉴id가 있어야됩니다.
         * */
        final Menu menu = menuRepository.findById(menuId)
            .orElseThrow(NoSuchElementException::new);
        /**
         * 메뉴 가격은 [상품 가격과 상품 개수를 곱한 값] 보다  작거나 같아야 된다.
         * */
        for (final MenuProduct menuProduct : menu.getMenuProducts()) {
            final BigDecimal sum = menuProduct.getProduct()
                .getPrice()
                .multiply(BigDecimal.valueOf(menuProduct.getQuantity()));
            if (price.compareTo(sum) > 0) {
                throw new IllegalArgumentException();
            }
        }
        /**
         * 메뉴의 가격을 바꾼다.
         * */
        menu.setPrice(price);
        return menu;
    }

    @Transactional
    public Menu display(final UUID menuId) {
        /**
         * 메뉴 테이블에 메뉴 id 가 등록 되어 있어야 됩니다.
         * */
        final Menu menu = menuRepository.findById(menuId)
            .orElseThrow(NoSuchElementException::new);
        /**
         * 메뉴 가격이 상품 개수와 상품 가격을 곱한 값 보다 작거나 같아야 됩니다.
         * */
        for (final MenuProduct menuProduct : menu.getMenuProducts()) {
            final BigDecimal sum = menuProduct.getProduct()
                .getPrice()
                .multiply(BigDecimal.valueOf(menuProduct.getQuantity()));
            if (menu.getPrice().compareTo(sum) > 0) {
                throw new IllegalStateException();
            }
        }
        menu.setDisplayed(true);
        return menu;
    }

    @Transactional
    public Menu hide(final UUID menuId) {
        /**
         * 메뉴테이블에 메뉴가 있어야 합니다.
         * */
        final Menu menu = menuRepository.findById(menuId)
            .orElseThrow(NoSuchElementException::new);
        menu.setDisplayed(false);
        return menu;
    }

    @Transactional(readOnly = true)
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
}
