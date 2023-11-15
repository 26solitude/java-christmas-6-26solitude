package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Controller {
    public void Start() {
        View view = new View();
        Model model = new Model();
        int date = InputDate(view, model);
        String[] Menus = InputMenu(view, model);
        view.ShowInputMenu(Menus);
        view.ShowTotalPrice(CalcTotalPrice(Menus));

    }

    private int CalcTotalPrice(String[] menus) {
        int TotalPrice = 0;
        for (String menuItem : menus) {
            String[] parts = menuItem.split("-");
            String menuName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            // Menu 객체로 저장하여 보여주기
            for (Model.Menu menu : Model.Menu.values()) {
                if (menu.getName().equals(menuName)) {
                    for (int i = 0; i < quantity; i++) {
                        TotalPrice += menu.getPrice();
                    }
                }
            }
        }

        return TotalPrice;
    }

    private int InputDate(View view, Model model) {
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


    private String[] InputMenu(View view, Model model) {
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
}
