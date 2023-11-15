package christmas;

import java.text.DecimalFormat;

import static christmas.Model.CalcInputMenu;

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
        CalcInputMenu(menus);
    }

    static void ShowInputMenus(String name, int quantity) {
        System.out.println(name + " " + quantity + "개");
    }

    public String printFormat(int price){
        DecimalFormat decFormat = new DecimalFormat("###,###");
        String formatPrice = decFormat.format(price);
        return formatPrice;
    }


    public void ShowTotalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(printFormat(totalPrice));
    }

    public void ShowGift(int gift, int totalPrice) {
        if(gift == 25000) System.out.println("<증정 메뉴>\n샴페인 1개");
        if(gift == 0 || totalPrice<10000) System.out.println("<증정 메뉴>\n없음");
    }

    public void TotalDiscountList(int christmasDiscount, int weekendDiscount, int holydayDiscount, int specialDiscount, int totalPrice, int gift) {
        System.out.println("<혜택 내역>");
        if(totalPrice<10000){
            System.out.println("없음");
            return;
        }
        if(christmasDiscount != 0) System.out.println("크리스마스 디데이 할인 : -"+printFormat(christmasDiscount)+"원");
        if(weekendDiscount != 0) System.out.println("평일 할인 : -"+printFormat(weekendDiscount)+"원");
        if(holydayDiscount != 0) System.out.println("주말 할인 : -"+printFormat(holydayDiscount)+"원");
        if(specialDiscount != 0) System.out.println("특별 할인 : -"+printFormat(specialDiscount)+"원");
        if(gift != 0) System.out.println("증정 이벤트 : -"+printFormat(gift)+"원");
    }

    public void TotalDiscountPrice(int totalDiscount, int totalPrice, int gift) {
        if (totalPrice <10000){
            System.out.println("<총혜택 금액>\n0원");
            System.out.println("<할인 후 예상 결제 금액>\n"+printFormat(totalPrice)+"원");
            return;
        }
        System.out.println("<총혜택 금액>\n-"+totalDiscount+"원");
        if(gift!=0) System.out.println("<할인 후 예상 결제 금액>\n"+printFormat((totalPrice-totalDiscount+gift))+"원");
        if(gift==0) System.out.println("<할인 후 예상 결제 금액>\n"+printFormat((totalPrice-totalDiscount))+"원");
    }

    public void ShowBadge(String badge) {
        System.out.println("<12월 이벤트 배지>\n"+badge);
    }
}
