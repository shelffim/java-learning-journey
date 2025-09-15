package mid1.assignment3.com.schedule.event;

import mid1.assignment3.com.schedule.time.MyLocalDate;

public class Event {
    private final String name;
    private final MyLocalDate date;

    public Event(String name, MyLocalDate date) {
        this.name = name;
        this.date = date;
    }

    public Event findEvent(int year, int month) {
        if ((this.date.getYear() == year) && (this.date.getMonth() == month)) {
            return this;
        }

        return null;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
