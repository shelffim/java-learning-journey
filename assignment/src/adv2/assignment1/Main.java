package adv2.assignment1;

import adv2.assignment1.domain.Expense;
import adv2.assignment1.service.ExpenseManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ExpenseManager manager = new ExpenseManager();
        String filename = "adv2/expenses.dat";
        String reportFilename = "adv2/report.txt";

        // 1. 테스트 데이터 생성
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("2025-09-26", "점심 식사", 12000, "동료와 함께"));
        expenses.add(new Expense("2025-09-26", "커피", 4500, "아이스 아메리카노"));
        expenses.add(new Expense("2025-09-25", "책 구매", 21000, "자바 I/O 완전 정복"));
        expenses.add(new Expense("2025-09-24", "교통비", 5400, "지하철 이용"));

        // 2. 지출 내역을 바이너리 파일에 저장
        manager.saveExpenses(expenses, filename);
        System.out.println("지출 내역이 " + filename + " 파일에 저장되었습니다.");

        // 3. 바이너리 파일에서 지출 내역 불러오기
        List<Expense> loadedExpenses = manager.loadExpenses(filename);
        System.out.println(filename + " 파일로부터 지출 내역을 불러왔습니다.");

        // 4. 불러온 내역으로 텍스트 보고서 생성
        manager.generateReport(loadedExpenses, reportFilename);
        System.out.println("상세 보고서가 " + reportFilename + " 파일로 생성되었습니다.");
    }
}
