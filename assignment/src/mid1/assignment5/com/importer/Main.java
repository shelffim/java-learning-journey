package mid1.assignment5.com.importer;

import mid1.assignment5.com.importer.data.User;
import mid1.assignment5.com.importer.data.UserRepository;
import mid1.assignment5.com.importer.exception.DataLoadException;
import mid1.assignment5.com.importer.service.DataSource;
import mid1.assignment5.com.importer.service.UserService;

public class Main {

    public static void main(String[] args) {
        UserRepository repository = new UserRepository();
        UserService userService = new UserService(repository);

        System.out.println("== 1. 정상 데이터 임포트 ==");
        DataSource source1 = new DataSource(new String[]{
                "user1,Alice,30",
                "user2,Bob,25"
        });
        runImport(userService, source1);
        printAllUsers(repository);

        System.out.println("\n== 2. 일부 비정상 데이터 포함 임포트 ==");
        DataSource source2 = new DataSource(new String[]{
                "user3,Charlie,35",
                "user4,David", // 형식이 잘못된 데이터
                "user5,Eve,22,extra" // 형식이 잘못된 데이터
        });
        runImport(userService, source2);
        printAllUsers(repository);

        System.out.println("\n== 3. 비정상 데이터 소스 임포트 ==");
        DataSource source3 = null; // 데이터 소스 자체가 null
        runImport(userService, source3);
        printAllUsers(repository);
    }

    private static void runImport(UserService userService, DataSource dataSource) {
        try {
            userService.importUsers(dataSource);
        } catch (DataLoadException e) {
            System.out.println("[오류] 데이터 로드에 실패했습니다. 메시지: " + e.getMessage());
        }
    }

    private static void printAllUsers(UserRepository repository) {
        System.out.println("--- 현재 등록된 사용자 ---");
        for (User user : repository.getAllUsers()) {
            System.out.println(user);
        }
        System.out.println("----------------------");
    }
}
