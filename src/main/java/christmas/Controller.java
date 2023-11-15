package christmas;

public class Controller {
    public void Start() {
        View view = new View();
        Model model = new Model();
        int date = model.InputDate(view, model);
        String[] Menus = model.InputMenu(view, model);
        view.ShowInputMenu(Menus);
        view.ShowTotalPrice(model.CalcTotalPrice(Menus));
        view.PreviewEventBenefitMessage(date);
        CalcDiscount(view, model, date, Menus);
    }

    private static void CalcDiscount(View view, Model model, int date, String[] Menus) {
        int totalPrice = model.CalcTotalPrice(Menus);
        int ChristmasDiscount = model.ChristmasDiscount(date);
        int WeekendDiscount = model.WeekendDiscount(Menus, date);
        int HolydayDiscount = model.HolydayDiscount(Menus, date);
        int SpecialDiscount = model.SpecialDiscount(date);
        int Gift = model.CheckGift(model.CalcTotalPrice(Menus));
        int TotalDiscount = model.TotalDiscount(ChristmasDiscount, WeekendDiscount, HolydayDiscount, SpecialDiscount, Gift);
        String Badge = model.CheckBadge(TotalDiscount);
        view.ShowGift(Gift, totalPrice);
        view.TotalDiscountList(ChristmasDiscount, WeekendDiscount, HolydayDiscount, SpecialDiscount, totalPrice, Gift);
        view.TotalDiscountPrice(TotalDiscount, totalPrice, Gift);
        view.ShowBadge(Badge);
    }


}
