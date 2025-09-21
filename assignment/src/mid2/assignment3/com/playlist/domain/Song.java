package mid2.assignment3.com.playlist.domain;

import java.util.Objects;

public class Song {

    private final String id;
    private final String title;
    private final String artist;

    public Song(String id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Song song = (Song) object;
        return Objects.equals(id, song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
