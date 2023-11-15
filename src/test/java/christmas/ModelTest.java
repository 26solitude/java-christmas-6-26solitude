package christmas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void checkValidMenu() {
        Model model = new Model();

        // 유효한 메뉴 테스트
        String validMenu = "타파스-2, 레드와인-1";
        assertDoesNotThrow(() -> model.CheckValidMenu(validMenu));

        // 유효하지 않은 메뉴 테스트
        String invalidMenu = "양송이수프-1, 아이스크림-3, 크리스마스파스타-0, 타파스-5, 샴페인-2, 샐러드-1";
        assertThrows(IllegalArgumentException.class, () -> model.CheckValidMenu(invalidMenu));

    }

    @Test
    void checkBadge() {
        Model model = new Model();

        // 할인 범위에 따른 Badge 확인
        assertEquals("별", model.CheckBadge(7000));
        assertEquals("트리", model.CheckBadge(15000));
        assertEquals("산타", model.CheckBadge(25000));
        assertEquals("없음", model.CheckBadge(3000));
    }

    @Test
    void checkValidDate() {
        Model model = new Model();

        // 유효한 날짜 테스트
        assertTrue(Model.CheckDateInt("10"));
        assertTrue(model.CheckDateRange("15"));

        // 유효하지 않은 날짜 테스트
        assertFalse(Model.CheckDateInt("invalid"));
        assertFalse(model.CheckDateRange("40"));
    }

    @Test
    void calcTotalPrice() {
        Model model = new Model();
        // 계산된 총 가격 테스트
        String[] menus = {"양송이수프-2", "레드와인-1"};
        assertEquals(72000, model.CalcTotalPrice(menus));

    }

    @Test
    void calcInputMenu() {        // 예제 메뉴 항목들 생성
        Model.Menu[] menus = {
                Model.Menu.ZERO_COLA,
                Model.Menu.RED_WINE,
                Model.Menu.CHAMPAGNE
        };

        // 예제 메뉴 항목 수량 설정
        int[] quantities = {2, 3, 1};

        // 총 금액 계산
        int totalPrice = 0;
        for (int i = 0; i < menus.length; i++) {
            totalPrice += menus[i].getPrice() * quantities[i];
        }

        assertEquals(totalPrice, Model.CalcTotalPrice(new String[]{"제로콜라-2", "레드와인-3", "샴페인-1"}));
    }

    @Test
    void totalDiscount() {
        assertEquals(31000, Model.TotalDiscount(1000, 20000, 5000, 5000, 0));
    }

    @Test
    void checkGift() {
        assertEquals(25000, Model.CheckGift(130000));
        assertEquals(0, Model.CheckGift(120000));
    }

    @Test
    void specialDiscount() {
        assertEquals(1000, Model.SpecialDiscount(3));
        assertEquals(1000, Model.SpecialDiscount(25));
        assertEquals(0, Model.SpecialDiscount(8));
    }

    @Test
    void holydayDiscount() {
        String[] menus = {"티본스테이크-2", "시저샐러드-3"};
        assertEquals(4046, Model.HolydayDiscount(menus, 1));
        assertEquals(0, Model.HolydayDiscount(menus, 4));
    }

    @Test
    void weekendDiscount() {
        String[] menus = {"초코케이크-2", "샴페인-1"};
        assertEquals(4046, Model.WeekendDiscount(menus, 3));
        assertEquals(0, Model.WeekendDiscount(menus, 8));
    }

    @Test
    void christmasDiscount() {
        assertEquals(3100, Model.ChristmasDiscount(22));
        assertEquals(3400, Model.ChristmasDiscount(25));
    }
}