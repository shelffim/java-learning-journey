package mid2.assignment2.com.migration.service;

import mid2.assignment2.com.migration.domain.User;
import mid2.assignment2.com.migration.strategy.MigrationStrategy;

import java.util.List;

public class MigrationService {
    MigrationStrategy strategy;
    List<User> list;

    public MigrationService(MigrationStrategy strategy, List<User> list) {
        this.strategy = strategy;
        this.list = list;
    }

    public void migrateUsers(LegacySystem legacySystem) {
        User[] allUsers = legacySystem.getAllUsers();
        this.strategy.migrate(allUsers, this.list);
    }
}
