package onboarding;

public class Problem3 {
    public static int solution(int number) {
        return countClaps(number);
    }

    private static int countClaps(int number) {
        int clapCounts = 0;

        for (int i = 1; i <= number; i++) {
            clapCounts += countTotal369(i);
        }
        return clapCounts;
    }

    private static int countTotal369(int number) {
        int count = 0;
        while (number > 0) {
            int first = number % 10;
            if (first != 0 && first % 3 == 0) count++;
            number /= 10;
        }
        return count;
    }
}
