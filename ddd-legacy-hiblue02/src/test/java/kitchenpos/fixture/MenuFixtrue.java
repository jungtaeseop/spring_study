package kitchenpos.fixture;

import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuGroup;
import kitchenpos.domain.MenuProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MenuFixtrue {
    private  final UUID DEFAULT_ID = UUID.randomUUID();

    private  final String DEFAULT_NAME = "기본 메뉴";

    private final MenuGroup DEFAULT_MENUGROUP = new MenuGroup();

    public static Menu create(String name, MenuGroup menuGroup, List<MenuProduct> menuProducts, BigDecimal price,boolean displayed){
        Menu menu = new Menu();
        menu.setId(UUID.randomUUID());
        menu.setName(name);
        menu.setMenuGroup(menuGroup);
        menu.setMenuProducts(menuProducts);
        menu.setPrice(price);
        menu.setDisplayed(displayed);
        return menu;
    }
}
