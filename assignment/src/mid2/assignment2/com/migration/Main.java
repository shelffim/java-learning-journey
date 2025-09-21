package mid2.assignment2.com.migration;

import mid2.assignment2.com.migration.domain.User;
import mid2.assignment2.com.migration.service.LegacySystem;
import mid2.assignment2.com.migration.service.MigrationService;
import mid2.assignment2.com.migration.strategy.DirectMigrationStrategy;
import mid2.assignment2.com.migration.strategy.MigrationStrategy;
import mid2.assignment2.com.migration.strategy.SortedMigrationStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 1. 준비: 레거시 시스템과 데이터를 담을 새로운 List들을 생성
        LegacySystem legacySystem = new LegacySystem();
        List<User> arrayListDestination = new ArrayList<>();
        List<User> linkedListDestination = new LinkedList<>();

        System.out.println("== 원본 데이터 ==");
        printUsers(legacySystem.getAllUsers());
        System.out.println("----------------------------------------");


        // 2. 시나리오 1: '순서대로' 마이그레이션 전략을 'ArrayList'에 주입하여 실행
        System.out.println("== [전략 1] 순서대로 마이그레이션 (to ArrayList) ==");
        MigrationStrategy directStrategy = new DirectMigrationStrategy();
        MigrationService directMigrationService = new MigrationService(directStrategy, arrayListDestination);
        directMigrationService.migrateUsers(legacySystem);

        System.out.println("== 마이그레이션 결과 (ArrayList) ==");
        printUsers(arrayListDestination);
        System.out.println("----------------------------------------");


        // 3. 시나리오 2: '정렬해서' 마이그레이션 전략을 'LinkedList'에 주입하여 실행
        System.out.println("== [전략 2] 정렬해서 마이그레이션 (to LinkedList) ==");
        MigrationStrategy sortedStrategy = new SortedMigrationStrategy();
        MigrationService sortedMigrationService = new MigrationService(sortedStrategy, linkedListDestination);
        sortedMigrationService.migrateUsers(legacySystem);

        System.out.println("== 마이그레이션 결과 (LinkedList) ==");
        printUsers(linkedListDestination);
        System.out.println("----------------------------------------");


        // 4. 보너스: equals/hashCode가 잘 구현되었는지 확인
        System.out.println("== 보너스: 정렬된 리스트에 'user3'이 있는지 확인 ==");
        User userToFind = new User("user3", "Charlie"); // name이 달라도 id가 같으면 같은 객체
        boolean isFound = linkedListDestination.contains(userToFind);
        System.out.println("결과: " + isFound);
    }

    private static void printUsers(List<User> list) {
        System.out.println("총 사용자 수: " + list.size());
        for (User user : list) {
            System.out.println(user);
        }
    }

    private static void printUsers(User[] users) {
        System.out.println("총 사용자 수: " + users.length);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
