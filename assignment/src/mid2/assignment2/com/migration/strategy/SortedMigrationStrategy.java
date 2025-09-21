package mid2.assignment2.com.migration.strategy;

import mid2.assignment2.com.migration.domain.User;

import java.util.List;

public class SortedMigrationStrategy implements MigrationStrategy {
    @Override
    public void migrate(User[] users, List<User> destination) {
        int[] usersIndex = new int[users.length];

        // User[] 정렬
        for (int i = 0; i < usersIndex.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < usersIndex.length; j++) {
                if (getIndex(users[minIdx]) > getIndex(users[j])) {
                    minIdx = j;
                }
            }
            User temp = users[minIdx];
            users[minIdx] = users[i];
            users[i] = temp;
        }

        // 삽입
        destination.addAll(List.of(users));
    }

    private static int getIndex(User user) {
        String id = user.getId();
        int index = Integer.parseInt(id.replaceAll("[^0-9]", ""));
        return index;
    }
}
