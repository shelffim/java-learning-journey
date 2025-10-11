package start.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientManagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 고정 크기 배열 대신 ArrayList를 사용하여 확장성을 개선한다.
        List<Patient> patients = new ArrayList<>();

        while(true) {
            System.out.println("========================================");
            System.out.println("1. 신규 환자 등록 | 2. 환자 목록 조회 | 3. 종료");
            System.out.println("========================================");
            System.out.print("입력:");
            int menu = scanner.nextInt();
            scanner.nextLine(); // nextInt() 호출 후 남은 개행 문자를 처리하기 위함.
            switch (menu) {
                case 1:
                    System.out.print("환자 번호를 입력하세요:");
                    String pNum = scanner.nextLine();
                    System.out.print("환자 이름을 입력하세요:");
                    String pName = scanner.nextLine();
                    System.out.print("환자 성별을 입력하세요:");
                    String pSex = scanner.nextLine();
                    System.out.print("환자 나이를 입력하세요:");
                    int pAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("환자 혈액형을 입력하세요:");
                    String pBloodType = scanner.nextLine();

                    // 생성자를 통해 누락된 정보 없이 모든 데이터가 저장된 Patient 객체를 생성한다.
                    patients.add(new Patient(pNum, pName, pSex, pAge, pBloodType));
                    System.out.println("신규 환자가 등록되었습니다.");
                    break;
                case 2:
                    // 향상된 for문을 사용하여 목록을 순회한다.
                    for(Patient patient: patients) {
                        // 캡슐화된 데이터에 접근하기 위해 Getter 메서드를 사용한다.
                        System.out.println("[" + patient.getpNum() + "] " + patient.getpName() + " (" + patient.getpSex() + ", " + patient.getpAge() + "세, " + patient.getpBloodType() + "형)");
                    }
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return; // main 메서드를 종료하여 프로그램 전체를 끝낸다.
            }
        }
    }
}
