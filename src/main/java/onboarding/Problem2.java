package onboarding;

import java.util.*;

public class Problem2 {
    public static String solution(String cryptogram) {
        return decoder(cryptogram);
    }

    private static String decoder(String cryptogram) {
        Stack<Character> stack = new Stack<>();

        for (char c : cryptogram.toCharArray()) {
            if (stack.empty() || stack.peek() != c) {
                stack.push(c);
                continue;
            }
            stack.pop();
        }

        return stackToString(stack);
    }

    private static String stackToString(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();

        for (char c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }
}
