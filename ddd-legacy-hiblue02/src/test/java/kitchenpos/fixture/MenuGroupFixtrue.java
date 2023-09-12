package kitchenpos.fixture;

import kitchenpos.domain.MenuGroup;

import java.util.UUID;


public class MenuGroupFixtrue {

    public static MenuGroup create(String name){
        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(UUID.randomUUID());
        menuGroup.setName(name);

        return menuGroup;
    }
}
