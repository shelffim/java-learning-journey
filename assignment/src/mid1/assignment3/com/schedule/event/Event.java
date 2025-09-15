package mid1.assignment3.com.schedule.event;

import mid1.assignment3.com.schedule.time.MyLocalDate;

public class Event {
    private final String name;
    private final MyLocalDate date;

    public Event(String name, MyLocalDate date) {
        this.name = name;
        this.date = date;
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonth();
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
