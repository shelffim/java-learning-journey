package mid2.assignment2.com.migration.strategy;

import mid2.assignment2.com.migration.domain.User;

import java.util.List;

public class DirectMigrationStrategy implements MigrationStrategy {

    @Override
    public void migrate(User[] users, List<User> destination) {
        destination.addAll(List.of(users));
    }
}
