package mid1.assignment1.user;

import java.util.Objects;

public class UserService {
    private final UserRepository repository =  new UserRepository();

    public void registerUser(User user) {
        boolean isSaved = repository.saveUser(user);

        if (isSaved) {
            System.out.println(user.getName() + " 님의 등록이 성공적으로 완료되었습니다.");
        } else {
            System.out.println("[오류] ID가 중복되거나 저장 공간이 부족하여 등록에 실패했습니다.");
        }
    }

    public void findUser(String id) {
        User foundUser = repository.findUserById(id);

        // Null 체크
        if (Objects.isNull(foundUser)) {
            System.out.println("존재 하지 않는 사용자입니다.");
            return;
        }

        System.out.println("검색된 사용자: " + foundUser);
    }
}
