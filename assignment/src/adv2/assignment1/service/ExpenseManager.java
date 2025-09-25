package adv2.assignment1.service;

import adv2.assignment1.domain.Expense;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ExpenseManager {

    private final List<Expense> expenseList = new ArrayList<>();

    public void saveExpenses(List<Expense> expenses, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);

        for (Expense expense : expenses) {
            String date = expense.getDate();
            String item = expense.getItem();
            int price = expense.getPrice();
            String memo = expense.getMemo();

            dos.writeUTF(date);
            dos.writeUTF(item);
            dos.writeInt(price);
            dos.writeUTF(memo);

        }

        dos.close();
    }

    public List<Expense> loadExpenses(String fileName) throws IOException {
        List<Expense> expenses = new ArrayList<>();

        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);

        while (true) {
            try {
                String date = dis.readUTF();
                String item = dis.readUTF();
                int price = dis.readInt();
                String memo = dis.readUTF();

                expenses.add(new Expense(date, item, price, memo));
            } catch (IOException e) {
                System.out.println("파일의 끝에 도착했습니다.");
                break;
            }
        }
        dis.close();
        return expenses;
    }

    public void generateReport(List<Expense> expenses, String reportFileName) throws IOException {
        FileWriter fw = new FileWriter(reportFileName, UTF_8);
        fw.write("========================================\n");
        fw.write("       지출 내역 보고서\n");
        fw.write("========================================\n");
        fw.write("날짜        | 항목          | 금액      | 메모\n");
        fw.write("----------------------------------------\n");
        for (Expense expense : expenses) {
            String formattedString = String.format("%s  | %s     |   %d원 | %s\n", expense.getDate(), expense.getItem(), expense.getPrice(), expense.getMemo());
            fw.write(formattedString);
        }
        fw.close();
    }
}
