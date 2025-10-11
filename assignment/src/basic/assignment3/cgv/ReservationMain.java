package basic.assignment3.cgv;

import basic.assignment3.cgv.movie.Movie;
import basic.assignment3.cgv.reserve.ReservationSystem;

public class ReservationMain {
    public static void main(String[] args) {
        Movie movie = new Movie("오펜하이머", 10);

        ReservationSystem system = new ReservationSystem(movie);

        // 예매 전 좌석을 확인하여 예매 후 좌석 상태 비교를 할 수 있게 함.
        System.out.println("=== 초기 좌석 상태 ===");
        system.printSeatStatus();
        System.out.println();

        // 1. 좌석 예매
        System.out.println("=== 좌석 예매 시도 ===");
        system.reserveSeat(3);
        system.reserveSeat(5);
        system.reserveSeat(8);
        System.out.println();

        // 2. 예매된 좌석 재예매 시도 (실패)
        System.out.println("=== 중복 예매 시도 ===");
        system.reserveSeat(5);
        System.out.println();

        // 3. 존재하지 않는 좌석 예매 시도 (실패)
        System.out.println("=== 없는 좌석 예매 시도 ===");
        system.reserveSeat(11);
        system.reserveSeat(0);
        System.out.println();

        // 4. 최종 좌석 상태 확인
        System.out.println("=== 최종 좌석 상태 ===");
        system.printSeatStatus();
        System.out.println();

        // 5. 전체 예매 건수 확인
        System.out.println("====================");
        System.out.println("총 예매된 좌석 수: " + ReservationSystem.getTotalReservations());
    }
}
