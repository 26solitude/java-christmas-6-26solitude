package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Controller {
    public void Start() {
        View view = new View();
        Model model = new Model();
        InputDate(view, model);
        InputMenu(view, model);

    }

    private void InputDate(View view, Model model) {
        view.StartMessage();
        while (true) {
            view.EnterDateMessage();
            String date = Console.readLine();
            try {
                model.CheckValidDate(date);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void InputMenu(View view, Model model) {
        while (true) {
            view.EnterMenuMessage();
            String Menu = Console.readLine();
            String[] Menus = Menu.split(",");

            boolean allValid = true;

            for (String menu : Menus) {
                try {
                    model.CheckValidMenu(menu);
                    break;
                } catch (IllegalArgumentException e) {
                    allValid = false;
                    System.out.println(e.getMessage());
                }
            }

            if (allValid) break;
        }
    }
}
