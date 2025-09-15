package mid1.assignment3.com.schedule.time;

public enum Holiday {
    NEW_YEAR(1,1),INDEPENDENCE_DAY(3,1),CHILDRENS_DAY(5,5);

    private final int month;
    private final int day;

    Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public static boolean isHoliDay(MyLocalDate date) {
        for(Holiday h: values()) {
            if (h.getMonth() == date.getMonth() && h.getDay() == date.getDay()) {
                return true;
            }
        }
        return false;
    }


}
