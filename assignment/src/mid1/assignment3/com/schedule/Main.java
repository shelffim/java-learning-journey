package mid1.assignment3.com.schedule;

import mid1.assignment3.com.schedule.event.Event;
import mid1.assignment3.com.schedule.event.ScheduleManager;
import mid1.assignment3.com.schedule.time.MyLocalDate;

public class Main {

    public static void main(String[] args) {
        // 1. MyLocalDate 생성 및 날짜 계산 테스트
        MyLocalDate date1 = new MyLocalDate(2025, 2, 27);
        System.out.println("기준 날짜: " + date1);

        // 3일을 더하면 3월 2일이 되어야 함 (2월은 28일까지)
        MyLocalDate date2 = date1.plusDays(3);
        System.out.println("3일 후 날짜: " + date2);

        // 날짜 비교 테스트
        System.out.println(date2 + "는 " + date1 + " 이후인가? " + date2.isAfter(date1));
        System.out.println(date1 + "는 " + date2 + " 이전인가? " + date1.isBefore(date2));
        System.out.println("--------------------");

        // 2. 공휴일 테스트
        MyLocalDate independenceDay = new MyLocalDate(2025, 3, 1);
        System.out.println("2025-03-01은 공휴일인가? " + independenceDay.isHoliday());
        System.out.println(date2 + "은 공휴일인가? " + date2.isHoliday());
        System.out.println("--------------------");

        // 3. 스케줄 관리 테스트
        ScheduleManager manager = new ScheduleManager();
        manager.addEvent(new Event("자바 스터디", new MyLocalDate(2025, 3, 2)));
        manager.addEvent(new Event("팀 프로젝트 회의", new MyLocalDate(2025, 3, 15)));
        manager.addEvent(new Event("알고리즘 문제 풀이", new MyLocalDate(2025, 4, 5)));

        System.out.println("== 2025년 3월 이벤트 목록 ==");
        Event[] marchEvents = manager.findEventsInMonth(2025, 3);
        for (Event event : marchEvents) {
            System.out.println(event);
        }
    }
}
