package mid1.assignment3.com.schedule.event;

public class ScheduleManager {
    private final int MAX_EVENT_LEN = 10;
    private final Event[] events = new Event[MAX_EVENT_LEN];
    private int count = 0;

    public void addEvent(Event event) {
        events[count++] = event;
    }

    public Event[] findEventsInMonth(int year, int month) {
        Event[] findEvents = new Event[MAX_EVENT_LEN];
        for (int i = 0; i < count; i++) {
            findEvents[i] = events[i].findEvent(year,month);
        }
        return findEvents;
    }



}
