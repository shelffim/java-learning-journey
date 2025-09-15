package mid1.assignment3.com.schedule.event;

import java.util.Arrays;

public class ScheduleManager {
    private final int MAX_EVENT_LEN = 10;
    private Event[] events = new Event[MAX_EVENT_LEN];
    private int count = 0;

    public void addEvent(Event event) {
        if (count >= events.length) {
            System.out.println("저장 공간이 가득 찼습니다. 배열의 크기를 2배로 늘립니다.");

            this.events = Arrays.copyOf(events, events.length * 2);
        }
        events[count++] = event;
    }

    public Event[] findEventsInMonth(int year, int month) {
        int foundCount = 0;
        Event[] findEvents = new Event[count];
        for (int i = 0; i < count; i++) {
            if((events[i].getYear() == year) && (events[i].getMonth() == month)) {
                findEvents[foundCount++] = events[i];
            }
        }
        return Arrays.copyOf(findEvents,foundCount);
    }



}
