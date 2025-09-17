package mid1.assignment5.com.importer.data;

import java.util.Arrays;

public class UserRepository {
    private final int INIT_LEN = 10;
    private User[] users = new User[INIT_LEN];
    private int count = 0;

    public User[] getAllUsers() {
        User[] notNullUser = new User[count];
        for (int i = 0; i < count; i++) {
            notNullUser[i] = users[i];
        }
        return notNullUser;
    }

    public void addUser(User user) {
        // 배열 꽉 차면 용량 늘리기
        if (INIT_LEN <= count) {
            this.users = Arrays.copyOf(users,INIT_LEN * 2);
        }

        users[count++] = user;
    }
}
