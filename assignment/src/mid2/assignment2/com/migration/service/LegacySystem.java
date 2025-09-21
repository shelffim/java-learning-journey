package mid2.assignment2.com.migration.service;

import mid2.assignment2.com.migration.domain.User;

public class LegacySystem {

    // user 입력 받기 추가
    private User[] users = {new User("user3", "Charlie"), new User("user1", "Alice"), new User("user2", "Bob"), new User("user 7", "chars")};

    public User[] getAllUsers() {
        return users;
    }
}
