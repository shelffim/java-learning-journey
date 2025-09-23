package mid2.assignment4.com.browser.domain;

import java.util.Objects;

public class Bookmark implements Comparable<Bookmark> {
    private final String url;
    private final String name;

    public Bookmark(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Bookmark bookmark = (Bookmark) object;
        return Objects.equals(url, bookmark.url);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url);
    }

    @Override
    public int compareTo(Bookmark o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
