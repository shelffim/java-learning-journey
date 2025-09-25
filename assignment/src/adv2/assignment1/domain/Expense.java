package adv2.assignment1.domain;

public class Expense {

    private final String date;
    private final String item;
    private final int price;
    private final String memo;

    public Expense(String date, String item, int price, String memo) {
        this.date = date;
        this.item = item;
        this.price = price;
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public String getMemo() {
        return memo;
    }
}
