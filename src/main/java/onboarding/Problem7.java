package onboarding;

import java.util.*;

public class Problem7 {

    private static class User {
        public String name;
        public Set<User> friends;
        public Map<User, Integer> visitCounts;

        public User(String name) {
            this.name = name;
            friends = new HashSet<>();
            visitCounts = new HashMap<>();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return this.name.equals(user.name);
        }

        public void addFriend(User friend) {
            friends.add(friend);
        }

        public void addVisitorCounts(User friend) {
            visitCounts.put(friend, visitCounts.getOrDefault(friend, 0) + 1);
        }

        public boolean isFriendWith(User user) {
            return friends.contains(user);
        }

        public int numberOfFriends() {
            return friends.size();
        }

        public int countsOfVisit(User user) {
            if (visitCounts.containsKey(user)) {
                return visitCounts.get(user);
            }
            return 0;
        }
    }

    private static class Manager {
        private final Map<String, User> userMap;

        public Manager() {
            userMap = new HashMap<>();
        }

        public void addRelationships(List<List<String>> relationships) {
            for (List<String> relationship : relationships) {
                addRelationship(relationship);
            }
        }

        public void addRelationship(List<String> relationship) {
            checkExistUsers(relationship);
            User user1 = userMap.get(relationship.get(0));
            User user2 = userMap.get(relationship.get(1));
            user1.addFriend(user2);
            user2.addFriend(user1);
        }

        public void addVisitors(String hostName, List<String> visitors) {
            for (String visitor : visitors) {
                addVisitor(hostName, visitor);
            }
        }

        public void addVisitor(String hostName, String visitorName) {
            checkExistUsers(List.of(hostName, visitorName));
            User host = userMap.get(hostName);
            User visitor = userMap.get(visitorName);
            host.addVisitorCounts(visitor);
        }

        public int numberOfDuplicateFriends(User user1, User user2) {
            Set<User> remainder = new HashSet<>(user1.friends);
            remainder.removeAll(user2.friends);
            return user1.numberOfFriends() - remainder.size();
        }

        public List<String> getRecommendList(String name) {
            User host = userMap.get(name);
            Map<String, Integer> recommendScoreMap = new HashMap<>();

            for (User user : userMap.values()) {
                if (host.equals(user)) continue;
                if (host.isFriendWith(user)) continue;
                int numberOfDup = numberOfDuplicateFriends(host, user);
                recommendScoreMap.put(user.name, numberOfDup);
            }

            for (Map.Entry<User, Integer> entry : host.visitCounts.entrySet()) {
                if (isFriendOrMe(host, entry.getKey())) continue;
                String key = entry.getKey().name;
                recommendScoreMap.put(key, recommendScoreMap.getOrDefault(key, 0) - entry.getValue());
            }

            List<String> recommendList = new ArrayList<>(recommendScoreMap.keySet());
            recommendList.sort((l1, l2) -> Integer.compare(recommendScoreMap.get(l2), recommendScoreMap.get(l1)));

            return recommendList;
        }

        private boolean isFriendOrMe(User user1, User user2) {
            return user1.equals(user2) || user1.isFriendWith(user2);
        }

        private void checkExistUsers(List<String> usersNames) {
            for (String name : usersNames) {
                checkExistUser(name);
            }
        }

        private void checkExistUser(String usersName) {
            if (userMap.containsKey(usersName)) return;
            userMap.put(usersName, new User(usersName));
        }
    }

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        Manager manager = new Manager();
        manager.addRelationships(friends);
        manager.addVisitors(user, visitors);

        return manager.getRecommendList(user);
    }
}
