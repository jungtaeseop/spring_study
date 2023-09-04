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
         * Objects.isNull price 가 null 이면 true
         * price.compareTo 비교  :  price 와 BigDecimal.ZERO 같으면 0  , price가 작으면 -1
         * 결론 : price 가 마이너스이면 true
         */
        if (Objects.isNull(price) || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        /**
         * Menu에서 메뉴그룹 id 찾아 menuGroup을 찾는다. 없으면 Nosuch 오류
         */
        final MenuGroup menuGroup = menuGroupRepository.findById(request.getMenuGroupId())
            .orElseThrow(NoSuchElementException::new);
        /**
         * 해당 menu에 있는 menu_상품을 선언한다.
         * 찾은 menu_상품이 null 이면 true , List<MenuProduct> isEmpty(빈 값) 이면 true
         * 메뉴_상품이 있어야된다.
         */
        final List<MenuProduct> menuProductRequests = request.getMenuProducts();

        if (Objects.isNull(menuProductRequests) || menuProductRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        /**
         *  메뉴 -> 메뉴 구성 상품 List에서 상품 id를 가져와 Products 선언
         *  상품 리스트와 메뉴구성상품 의 사이즈가 다르다면 true
         *  상품 리스트와 메뉴구성상품의 사이즈가 같아 된다.
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
         * 해당 메뉴에 있는 메뉴상품 구성 List를 하나씩 돌려가며
         * 메뉴_상품구성의 개수를 가져와 quantity에 저장 , quantity가 0 보다 작으면 오류
         * 메뉴구성상품에서 상품 id 검색해 product 가져옴
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
