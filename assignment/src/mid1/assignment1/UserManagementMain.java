package mid1.assignment1;

import mid1.assignment1.user.User;
import mid1.assignment1.user.UserRepository;
import mid1.assignment1.user.UserService;

public class UserManagementMain {

    public static void main(String[] args) {
        User user1 = new User("user1", "홍길동");
        User user1_duplicate = new User("user1", "임꺽정"); // ID는 같고, 이름은 다름
        User user2 = new User("user2", "이순신");

        // 1. 동일성(==) vs 동등성(equals) 비교
        System.out.println("## 동일성(==) 비교: " + (user1 == user1_duplicate));
        System.out.println("## 동등성(equals) 비교: " + user1.equals(user1_duplicate));
        System.out.println();

        // 2. UserService 테스트
        UserService service = new UserService();

        System.out.println("## 사용자 등록 시도");
        service.registerUser(user1);
        service.registerUser(user2);
        System.out.println();

        // 3. 중복 ID 저장 시도
        System.out.println("## 중복 ID 저장 시도");
        service.registerUser(user1_duplicate); // user1과 동등하므로 저장되면 안 됨
        System.out.println();

        // 4. ID로 사용자 검색 및 검색 결과 및 toString() 확인
        System.out.println("## ID로 사용자 검색");
        service.findUser("user1");
        System.out.println();

        // 5. 존재하지 않는 ID로 사용자 검색 및 검색 결과 및 toString() 확인
        System.out.println("## 잘못된 ID로 사용자 검색");
        service.findUser("u");

    }
}