package mid1.assignment3.com.schedule.time;

//1/31, 2/28, 3/31, 4/30, 5/31, 6/30, 7/31, 8/31, 9/30, 10/31. 11/30. 12/31
public class MyLocalDate {
    private final int year;
    private final int month;
    private final int day;

    // 해당월이 몇일인지
    private final static int[] perMonthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int TOTAL_MONTHS = 12;
    private final int TOTAL_DAYS = 365;

    public MyLocalDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyLocalDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    public MyLocalDate plusDays(int day) {
        // 1달 이상, 1년 이상 일 추가시
        MyLocalDate restDay = bigPlusDays(day);
        int plusYear = this.year + restDay.getYear();
        int plusMonth = this.month + restDay.getMonth();
        // 밑에 if문이 중복인데 해결 방법 모르겠음
        if (plusMonth > TOTAL_MONTHS) {
            plusMonth %= TOTAL_MONTHS;
            plusYear++;
        }
        int plusDay = this.day + restDay.getDay();
        if (plusDay > perMonthDays[plusMonth]) {
            plusDay -= perMonthDays[plusMonth++];
            if (plusMonth > TOTAL_MONTHS) {
                plusMonth = 1;
                plusYear++;
            }
        }
        return new MyLocalDate(plusYear,plusMonth,plusDay);
    }

    private void isNextYear(int plusMonth) {

    }

    // [더할 년,더할 월,남은 일)
    private MyLocalDate bigPlusDays(int day) {
        // 입력값이 1년(=365)일 이상인지
        int plusYear = day / TOTAL_DAYS;
        int plusMonth = 0;
        int dayModYear = day % TOTAL_DAYS;
        int month = this.month;
        while (dayModYear - perMonthDays[month] > 0) {
            dayModYear -= perMonthDays[month++];
            plusMonth++;
            if (month > TOTAL_MONTHS) {
                month = 1;
            }
        }
        int plusDay = dayModYear;
        return new MyLocalDate(plusYear, plusMonth, plusDay);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public boolean isAfter(MyLocalDate date) {
        if (this.getYear() > date.getYear()) {
            return true;
        } else if(this.getMonth() > date.getMonth()) {
            return true;
        } else {
            return this.getDay() > date.getDay();
        }
    }

    public boolean isBefore(MyLocalDate date) {
        if (this.getYear() < date.getYear()) {
            return true;
        } else if(this.getMonth() < date.getMonth()) {
            return true;
        } else {
            return this.getDay() < date.getDay();
        }
    }

    public boolean isHoliday() {
        return Holiday.isHoliDay(this);
    }

}
