package basic.assignment3.cgv.reserve;

import basic.assignment3.cgv.movie.Movie;

public class ReservationSystem {
    private final Movie movie;
    private final boolean[] seats;
    private static int totalReservations = 0;

    public ReservationSystem(Movie movie) {
        this.movie = movie;
        seats = new boolean[this.movie.getTotalSeats()];
    }

    public void reserveSeat(int seatNumber) {

        if (1 > seatNumber || seatNumber > seats.length) {
            System.out.println("잘못된 좌석 번호입니다");
            return;
        }

        // 숫자를 인덱스로 변환
        int seatIndex = toIndex(seatNumber);

        if (seats[seatIndex]) {
            System.out.println("이미 예약된 좌석입니다.");
            return;
        }

        System.out.println("좌석 " + seatNumber + "번이 예매되었습니다.");
        seats[seatIndex] = true;
        totalReservations++;
    }

    // 좌석 번호를 배열 인덱스로 변환해주는 private 메서드
    private int toIndex(int seatNumber) {
        return seatNumber - 1;
    }

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
