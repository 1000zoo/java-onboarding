package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem5 {

    private static final int[] bills = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};

    public static List<Integer> solution(int money) {
        List<Integer> answer = new ArrayList<>();
        withdraw(money, 0, answer);
        return answer;
    }

    private static void withdraw(int currentMoney, int billsIndex, List<Integer> wallet) {
        if (billsIndex == 9) return;
        wallet.add(currentMoney / bills[billsIndex]);
        withdraw(currentMoney % bills[billsIndex], billsIndex + 1, wallet);
    }

}
