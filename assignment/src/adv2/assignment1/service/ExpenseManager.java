package adv2.assignment1.service;

import adv2.assignment1.domain.Expense;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ExpenseManager {

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

        try {
            while (true) {
                String date = dis.readUTF();
                String item = dis.readUTF();
                int price = dis.readInt();
                String memo = dis.readUTF();

                expenses.add(new Expense(date, item, price, memo));
            }
        }catch (EOFException e) {

        }
        dis.close();
        return expenses;
    }

    public void generateReport(List<Expense> expenses, String reportFileName) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(reportFileName, UTF_8));
        pw.println("========================================");
        pw.println("       지출 내역 보고서");
        pw.println("========================================");
        pw.println("날짜        | 항목          | 금액      | 메모");
        pw.println("----------------------------------------");
        for (Expense expense : expenses) {
            pw.printf("%s  | %s     |   %d원 | %s\n", expense.getDate(), expense.getItem(), expense.getPrice(), expense.getMemo());
        }
        pw.close();
    }
}
