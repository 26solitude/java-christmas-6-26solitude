package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Controller {
    public void Start() {
        View view = new View();
        Model model = new Model();
        InputDate(view, model);

    }

    private void InputDate(View view, Model model) {
        view.StartMessage();
        while (true) {
            view.EnterDateMessage();
            String date = Console.readLine();
            try {
                model.CheckValidDate(date);
                break;
            }catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }


    private String EnterMenu() {
        String Menu = Console.readLine();

        return Menu;
    }
}
