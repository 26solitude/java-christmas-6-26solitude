package christmas;

public class View {

    public void StartMessage(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다");
    }
    public void EnterDateMessage(){
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void EnterMenuMessage(){
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void PreviewEventBenefitMessage(int VisitDay){
        System.out.println("12월 " + VisitDay + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void ShowInputMenu(String[] menus) {
        System.out.println("<주문 메뉴>");
        for (String menuItem : menus) {
            String[] parts = menuItem.split("-");
            String menuName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            // Menu 객체로 저장하여 보여주기
            for (Model.Menu menu : Model.Menu.values()) {
                if (menu.getName().equals(menuName)) {
                    System.out.println(menu.getName() + " " + quantity + "개");
                    break;
                }
            }
        }
    }

}
