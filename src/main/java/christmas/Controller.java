package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Controller {
    public void Start() {
        View view = new View();
        Model model = new Model();
        int date = model.InputDate(view, model);
        String[] Menus = model.InputMenu(view, model);
        view.ShowInputMenu(Menus);
        view.ShowTotalPrice(model.CalcTotalPrice(Menus));
        model.CalcDiscount(Menus);
    }







}
