package mid1.assignment1.user;

public class UserRepository {
    private User[] users = new User[10];
    private int count = 0;

    public void saveUser(User newUser) {
        // 중복 확인
        for (int i = 0; i < count; i++) {
            if (users[i].equals(newUser)) {
                System.out.println("[오류] 이미 존재하는 ID입니다.");
                return;
            }
        }

        users[count++] = newUser;
    }

    public User findUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

}
