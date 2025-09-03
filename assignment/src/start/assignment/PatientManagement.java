package start.assignment;

import java.util.Scanner;

public class PatientManagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Patient[] patients = new Patient[10];
        int idx = 0;

        while(true) {
            System.out.println("========================================");
            System.out.println("1. 신규 환자 등록 | 2. 환자 목록 조회 | 3. 종료");
            System.out.println("========================================");
            System.out.print("입력:");
            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    if (idx < patients.length) {
                        scanner.nextLine();
                        patients[idx] = new Patient();
                        System.out.print("환자 번호를 입력하세요:");
                        patients[idx].pNum = scanner.nextLine();
                        System.out.print("환자 이름을 입력하세요:");
                        patients[idx].pName = scanner.nextLine();
                        System.out.print("환자 성별을 입력하세요:");
                        patients[idx].pSex = scanner.nextLine();
                        System.out.print("환자 나이를 입력하세요:");
                        patients[idx].pAge = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("환자 혈액형을 입력하세요:");
                        patients[idx].pBloodType = scanner.nextLine();
                        ++idx;
                    } else {
                        System.out.println("환자 수량이 꽉 찼습니다.");
                    }
                    break;
                case 2:
                    for(int i = 0; i < idx; ++i) {
                        String var10001 = patients[i].pNum;
                        System.out.println("[" + var10001 + "] " + patients[i].pName + " (" + patients[i].pSex + ", " + patients[i].pAge + "세, " + patients[i].pBloodType + "형)");
                    }
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }
}
