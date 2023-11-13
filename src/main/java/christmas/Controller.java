package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Controller {
    public void Start() {
        View view = new View();
        InputDateAndMenu(view);

    }

    private void InputDateAndMenu(View view) {
        view.EnterDateMessage();
        int EnterDate = EnterDate();
        view.EnterMenuMessage();
        String EnterMenu = EnterMenu();
        view.PreviewEventBenefitMessage(EnterDate);
    }

    private int EnterDate() {
        int EnterDate = Integer.parseInt(Console.readLine());
        return EnterDate;
    }

    private String EnterMenu() {
        String EnterMenu = Console.readLine();
        return EnterMenu;
    }
}
