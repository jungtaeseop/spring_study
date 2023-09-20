package kitchenpos.fixture;

import kitchenpos.domain.MenuGroup;

import java.util.UUID;


public class MenuGroupFixtrue {

    private static final String DEFAULT_NAME = "기본 메뉴 그룹";



    public static MenuGroup create(String name){
        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(UUID.randomUUID());
        menuGroup.setName(name);

        return menuGroup;
    }

    public static MenuGroup create(){
        return create(DEFAULT_NAME);
    }
}
