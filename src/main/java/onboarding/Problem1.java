package onboarding;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Problem1 {

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        if (pobi.size() != 2 || crong.size() != 2) return -1;
        if (!isValidPages(pobi) || !isValidPages(crong)) return -1;
        int pobiScore = getMaxPageScore(pobi);
        int crongScore = getMaxPageScore(crong);

        if (pobiScore > crongScore) return 1;
        if (pobiScore < crongScore) return 2;
        return 0;
    }

    private static boolean isValidPages(List<Integer> pages) {
        int left = pages.get(0);
        int right = pages.get(1);
        return left < right && right - left == 1;
    }

    private static int getMaxPageScore(List<Integer> pages) {
        return pages.stream().reduce((a, b) -> Math.max(getMaxScore(a), getMaxScore(b))).orElse(0);
    }

    private static int getMaxScore(int page) {
        int[] split = toIntegerArray(page);
        int sum = IntStream.of(split).sum();
        int mul = IntStream.of(split).reduce((a, b) -> a * b).orElse(1);
        return Math.max(sum, mul);
    }

    private static int[] toIntegerArray(int page) {
        String temp = String.valueOf(page);
        return Stream.of(temp.split("")).mapToInt(Integer::parseInt).toArray();
    }
}