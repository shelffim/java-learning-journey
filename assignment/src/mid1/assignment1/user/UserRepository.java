package mid1.assignment1.user;

public class UserRepository {
    //매직 넘버 개선
    private static int DEFAULT_CAPACITY = 10;
    private User[] users = new User[DEFAULT_CAPACITY];
    private int count = 0;

    public boolean saveUser(User newUser) {
        // 공간 확인
        if (count >= users.length) {
            return false;
        }

        // 중복 확인
        for (int i = 0; i < count; i++) {
            if (users[i].equals(newUser)) {
                return false;
            }
        }

        users[count++] = newUser;
        return true;
    }

    public User findUserById(String id) {
        for (int i = 0; i < count; i++) {
            if (users[i].getId().equals(id)) {
                return users[i];
            }
        }

        return null;
    }
}
