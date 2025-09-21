package mid2.assignment2.com.migration.strategy;

import mid2.assignment2.com.migration.domain.User;

import java.util.List;

public interface MigrationStrategy {
    void migrate(User[] users, List<User> destination);
}
