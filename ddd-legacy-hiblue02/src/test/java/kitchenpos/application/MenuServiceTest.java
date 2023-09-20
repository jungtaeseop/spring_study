package kitchenpos.application;

import kitchenpos.domain.*;
import kitchenpos.fixture.MenuGroupFixtrue;
import kitchenpos.fixture.ProductServiceFixtrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private MenuGroupRepository menuGroupRepository;

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @DisplayName("메뉴를 생성하려면 메뉴 그룹과 상품이 미리 등록되어 있어야 합니다.")
    @Test
    public void 메뉴_그룹과_상품이_미리_등록(){
        //given

        MenuGroup menuGroup = MenuGroupFixtrue.create("치킨 모둠");
        given(menuGroupRepository.save(menuGroup)).willReturn(menuGroup);

        Product product = new ProductServiceFixtrue().create(BigDecimal.valueOf(100));
        given(productRepository.save(any())).willReturn(product);

        Menu menu = new


        //when
        //menuService.create();

        //then
    }
}