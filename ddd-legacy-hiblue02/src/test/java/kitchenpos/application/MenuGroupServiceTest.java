package kitchenpos.application;

import kitchenpos.domain.MenuGroup;
import kitchenpos.domain.MenuGroupRepository;
import kitchenpos.fixture.MenuGroupFixtrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuGroupServiceTest {
    @InjectMocks
    private MenuGroupService menuGroupService;

    @Mock
    private MenuGroupRepository menuGroupRepository;

    @DisplayName("메뉴 그룹을 생성한다.")
    @Test
    void menuGroupCreateTest(){
        //given
        MenuGroup menuGroup = MenuGroupFixtrue.create("기본 메뉴");
        given(menuGroupRepository.save(any())).willReturn(menuGroup);

        //when
        MenuGroup createdMenuGroup = menuGroupService.create(menuGroup);


        //then
        assertThat(menuGroup).isEqualTo(createdMenuGroup);
    }

    @DisplayName("메뉴 그룹 이름이 없을 경우 실패")
    @Test
    void menuGroupCreateFailTest(){
        //given
        MenuGroup menuGroup = MenuGroupFixtrue.create("");

        //when
        //then
        assertThatThrownBy(()-> menuGroupService.create(menuGroup))
                .isInstanceOf(IllegalArgumentException.class);

    }
}