package basic.assignment3.cgv.movie;

public class Movie {
    private final String title;
    private final int totalSeats;

    public Movie(String title, int totalSeats) {
        this.title = title;
        this.totalSeats = totalSeats;
    }

    public String getTitle() {
        return title;
    }

    public int getTotalSeats() {
        return totalSeats;
    }
}
