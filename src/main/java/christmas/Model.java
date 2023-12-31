package christmas;

import camp.nextstep.edu.missionutils.Console;

import static christmas.View.ShowInputMenus;

public class Model {
    public void CheckValidMenu(String menu) {
        String[] orders = menu.split(",");
        boolean isValid = true;
        boolean checkOnlyDrink = false;
        int totalQuantity = 0;

        for (String order : orders) {
            isValid = isValidOrder(order, totalQuantity, checkOnlyDrink);
            if (!isValid) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private boolean isValidOrder(String order, int totalQuantity, boolean checkOnlyDrink) {
        String[] parts = order.split("-");
        if (parts.length > 20) throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        if (parts.length != 2) return false;

        String menuName = parts[0].trim();
        int quantity;

        if (getValidOrder(totalQuantity, parts)) return false;

        return isMenuExists(menuName, checkOnlyDrink);
    }

    private static boolean getValidOrder(int totalQuantity, String[] parts) {
        int quantity;
        try {
            quantity = Integer.parseInt(parts[1].trim());
            totalQuantity += quantity;
            if (quantity <= 0 || totalQuantity > 20) return true;
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private boolean isMenuExists(String menuName, boolean checkOnlyDrink) {
        boolean menuExists = false;
        for (Menu menuObj : Menu.values()) {
            if (menuObj.getName().equals(menuName)) {
                menuExists = true;
                if (!menuObj.getCategory().equals(Category.DRINK)) {
                    checkOnlyDrink = true;
                }
                break;
            }
        }

        return menuExists && checkOnlyDrink;
    }



    public String CheckBadge(int totalDiscount) {
        if (totalDiscount >= 5000 && totalDiscount < 10000) return "별";
        if (totalDiscount >= 10000 && totalDiscount < 20000) return "트리";
        if (totalDiscount >= 20000) return "산타";
        return "없음";
    }

    public enum Menu {
        // 애피타이저
        YANG_SONG_I_SOUP("양송이수프", 6000, Category.APPETIZER),
        TAPAS("타파스", 5500, Category.APPETIZER),
        CAESAR_SALAD("시저샐러드", 8000, Category.APPETIZER),

        // 메인
        T_BONE_STEAK("티본스테이크", 55000, Category.MAIN),
        BBQ_RIBS("바비큐립", 54000, Category.MAIN),
        SEAFOOD_PASTA("해산물파스타", 35000, Category.MAIN),
        CHRISTMAS_PASTA("크리스마스파스타", 25000, Category.MAIN),

        // 디저트
        CHOCO_CAKE("초코케이크", 15000, Category.DESSERT),
        ICE_CREAM("아이스크림", 5000, Category.DESSERT),

        // 음료
        ZERO_COLA("제로콜라", 3000, Category.DRINK),
        RED_WINE("레드와인", 60000, Category.DRINK),
        CHAMPAGNE("샴페인", 25000, Category.DRINK);

        private final String name;
        private final int price;
        private final Category category;

        Menu(String name, int price, Category category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public Category getCategory() {
            return category;
        }
    }

    // 카테고리 Enum
    public enum Category {
        APPETIZER,
        MAIN,
        DESSERT,
        DRINK
    }


    void CheckValidDate(String date) {
        if (!CheckDateInt(date) || !CheckDateRange(date)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    boolean CheckDateRange(String date) {
        int checkMenu = Integer.parseInt(date);
        return checkMenu >= 1 && checkMenu <= 31;
    }

    static boolean CheckDateInt(String date) {
        try {
            Integer.parseInt(date);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static int CalcTotalPrice(String[] menus) {
        int TotalPrice = 0;
        for (String menuItem : menus) {
            String[] parts = menuItem.split("-");
            String menuName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            // Menu 객체로 저장하여 보여주기
            TotalPrice = getTotalPrice(menuName, quantity, TotalPrice);
        }
        return TotalPrice;
    }

    private static int getTotalPrice(String menuName, int quantity, int TotalPrice) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                for (int i = 0; i < quantity; i++) {
                    TotalPrice += menu.getPrice();
                }
            }
        }
        return TotalPrice;
    }

    int InputDate(View view, Model model) {
        view.StartMessage();
        String date;
        while (true) {
            view.EnterDateMessage();
            date = Console.readLine();
            try {
                model.CheckValidDate(date);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return Integer.parseInt(date);
    }

    String[] InputMenu(View view, Model model) {
        String[] Menus;
        while (true) {
            view.EnterMenuMessage();
            String Menu = Console.readLine();
            Menus = Menu.split(",");

            boolean allValid = true;
            allValid = CheckValidMenu(model, Menus, allValid);

            if (allValid) break;
        }
        return Menus;
    }

    private static boolean CheckValidMenu(Model model, String[] Menus, boolean allValid) {
        for (String menu : Menus) {
            try {
                model.CheckValidMenu(menu);
                break;
            } catch (IllegalArgumentException e) {
                allValid = false;
                System.out.println(e.getMessage());
            }
        }
        return allValid;
    }

    static void CalcInputMenu(String[] menus) {
        for (String menuItem : menus) {
            String[] parts = menuItem.split("-");
            String menuName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            GetMenuInput(menuName, quantity);
        }
    }

    private static void GetMenuInput(String menuName, int quantity) {
        // Menu 객체로 저장하여 보여주기
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                ShowInputMenus(menu.getName(), quantity);
                break;
            }
        }
    }

    static int TotalDiscount(int christmasDiscount, int holydayDiscount, int weekendDiscount, int specialDiscount, int gift) {
        return christmasDiscount + holydayDiscount + weekendDiscount + specialDiscount + gift;
    }

    static int CheckGift(int i) {
        if (i > 120000) return 25000;
        return 0;
    }

    static int SpecialDiscount(int date) {
        if (date % 7 == 3 || date == 25) return 1000;
        return 0;
    }

    static int HolydayDiscount(String[] menus, int date) {
        int HolydayDiscount = 0;
        if (date % 7 == 1 || date % 7 == 2) {
            for (String menuItem : menus) {
                String[] parts = menuItem.split("-");
                String menuName = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].trim());

                HolydayDiscount = getHolydayDiscount(menuName, HolydayDiscount, quantity);
            }
        }
        return HolydayDiscount;
    }

    private static int getHolydayDiscount(String menuName, int HolydayDiscount, int quantity) {
        // Menu 객체로 저장하여 보여주기
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName) && menu.getCategory().equals(Category.MAIN)) {
                HolydayDiscount += 2023 * quantity;
                break;
            }
        }
        return HolydayDiscount;
    }

    static int WeekendDiscount(String[] menus, int date) {
        int WeekendDiscount = 0;
        if (date % 7 != 1 && date % 7 != 2) {
            for (String menuItem : menus) {
                String[] parts = menuItem.split("-");
                String menuName = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].trim());

                WeekendDiscount = getWeekendDiscount(menuName, quantity, WeekendDiscount);
            }
        }
        return WeekendDiscount;
    }

    private static int getWeekendDiscount(String menuName, int quantity, int WeekendDiscount) {
        // Menu 객체로 저장하여 보여주기
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName) && menu.getCategory().equals(Category.DESSERT)) {
                System.out.println(menuName + " " + quantity);
                WeekendDiscount += 2023 * quantity;
                break;
            }
        }
        return WeekendDiscount;
    }

    static int ChristmasDiscount(int date) {
        return 1000 + 100 * (date - 1);
    }


}
