package onboarding;

public class Problem4 {
    public static String solution(String word) {
        return convertString(word);
    }

    private static String convertString(String word) {
        StringBuilder sb = new StringBuilder();

        for (char c : word.toCharArray()) {
            sb.append(convertCharacter(c));
        }
        return sb.toString();
    }

    private static char convertCharacter(char c) {
        if (c == ' ') {
            return c;
        }
        return convertCase(c);
    }

    private static char convertCase(char c) {
        char converted = convertAlphabet(Character.toLowerCase(c));
        if (Character.isUpperCase(c)) {
            return Character.toUpperCase(converted);
        }
        return converted;
    }

    private static char convertAlphabet(char c) {
        return (char) ('z' - c + 'a');
    }
}
