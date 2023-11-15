package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Set;

public class Model {


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
