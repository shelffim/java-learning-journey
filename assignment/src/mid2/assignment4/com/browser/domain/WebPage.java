package mid2.assignment4.com.browser.domain;

public class WebPage {
    private final String url;
    private final String title;

    public WebPage(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
