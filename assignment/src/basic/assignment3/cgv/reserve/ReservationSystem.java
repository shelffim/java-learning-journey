package basic.assignment3.cgv.reserve;

import basic.assignment3.cgv.movie.Movie;

public class ReservationSystem {
    private final Movie movie;
    private final boolean[] seats;
    private static int totalReservations = 0;

    // 영화의 좌석 수만큼 예매 배열 초기화
    public ReservationSystem(Movie movie) {
        this.movie = movie;
        seats = new boolean[this.movie.getTotalSeats()];
    }

    /*
    * 지정한 좌석을 예매합니다.
    * seatNumber 예매할 좌석 번호 (1부터 시작)
    * 좌석 위치가 1보다 작거나, 전체 좌석 수를 넘어갈 경우, 이미 예약되어 있을 경우 에러 메시지 발송 후 메서드 종료
    */
    public void reserveSeat(int seatNumber) {

        if (1 > seatNumber || seatNumber > seats.length) {
            System.out.println("잘못된 좌석 번호입니다");
            return;
        }

        int seatIndex = toIndex(seatNumber);

        if (seats[seatIndex]) {
            System.out.println("이미 예약된 좌석입니다.");
            return;
        }

        System.out.println("좌석 " + seatNumber + "번이 예매되었습니다.");
        seats[seatIndex] = true;
        totalReservations++;
    }

    // 사용자 친화적인 좌석 번호(1부터)를 배열 인덱스(0부터)로 변환
    private int toIndex(int seatNumber) {
        return seatNumber - 1;
    }

    // 영화 정보와 함께 모든 좌석의 예매 상태를 출력합니다.
    public void printSeatStatus() {
        System.out.println("영화: " + movie.getTitle());
        for (int i = 0; i < seats.length; i++) {
            System.out.println("좌석 " + (i + 1) + ": " + (seats[i] ? "예매됨" : "비어있음") );
        }
    }

    public static int getTotalReservations() {
        return totalReservations;
    }
}
