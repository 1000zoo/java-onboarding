package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem6 {

    private static Map<String, Set<String>> nameEmailMap;

    public static List<String> solution(List<List<String>> forms) {
        nameEmailMap = new HashMap<>();
        recordNicknames(forms);
        return getDuplicateList();
    }

    private static List<String> getDuplicateList() {
        Set<String> duplicatedSet = new HashSet<>();

        for (Set<String> set : nameEmailMap.values()) {
            if (set.size() <= 1) continue;
            duplicatedSet.addAll(set);
        }

        return duplicatedSet.stream().sorted().collect(Collectors.toList());
    }

    private static void recordNicknames(List<List<String>> forms) {
        for (List<String> form : forms) {
            recordNicknames(form.get(0), form.get(1));
        }
    }

    private static void recordNicknames(String email, String nickname) {
        List<String> splitList = getSplitList(nickname);
        for (String split : splitList) {
            initListOf(split);
            nameEmailMap.get(split).add(email);
        }
    }

    private static void initListOf(String splitNickname) {
        if (nameEmailMap.containsKey(splitNickname)) return;
        nameEmailMap.put(splitNickname, new HashSet<>());
    }

    private static List<String> getSplitList(String nickname) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < nickname.length() - 1; i++) {
            list.add(nickname.substring(i, i + 2));
        }

        return list;
    }
}
