package christmas;

public class Model {
    public void CheckValidMenu(String menu) {
        String[] orders = menu.split(",");
        boolean isValid = true;
        boolean anyError = false;

        for (String order : orders) {
            String[] parts = order.split("-");
            if (parts.length != 2) {
                isValid = false;
                anyError = true;
                break;
            } else {
                String menuName = parts[0].trim();
                int quantity;

                try {
                    quantity = Integer.parseInt(parts[1].trim());
                    if (quantity <= 0) {
                        isValid = false;
                        anyError = true;
                        break;
                    }
                } catch (NumberFormatException e) {
                    isValid = false;
                    anyError = true;
                    break;
                }

                // 입력된 메뉴가 메뉴판에 있는지 확인
                boolean menuExists = false;
                for (Menu menuObj : Menu.values()) {
                    if (menuObj.getName().equals(menuName)) {
                        menuExists = true;
                        break;
                    }
                }

                if (!menuExists) {
                    isValid = false;
                    anyError = true;
                    break;
                }
            }
        }
        Result result = new Result(isValid, anyError);

        if (!result.isValid() && result.anyError()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private record Result(boolean isValid, boolean anyError) {
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

    private boolean CheckDateRange(String date) {
        int checkMenu = Integer.parseInt(date);
        return checkMenu >= 1 && checkMenu <= 31;
    }

    private static boolean CheckDateInt(String date) {
        try {
            Integer.parseInt(date);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
