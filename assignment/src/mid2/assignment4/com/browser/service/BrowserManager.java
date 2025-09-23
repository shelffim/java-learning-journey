package mid2.assignment4.com.browser.service;

import mid2.assignment4.com.browser.domain.Bookmark;
import mid2.assignment4.com.browser.domain.WebPage;
import mid2.assignment4.com.browser.service.sort.UrlComparator;

import java.util.*;

public class BrowserManager {
    private final Deque<WebPage> history;
    private final Map<String, Bookmark> bookmarks;

    public BrowserManager(Deque<WebPage> backStack, Map<String, Bookmark> bookmarks) {
        this.history = backStack;
        this.bookmarks = bookmarks;
    }

    public void visitPage(WebPage webPage) {
        System.out.println("페이지 방문: " + webPage);
        history.push(webPage);
    }

    public WebPage goBack() {
        if (history.peek() == null) {
            return null;
        }
        history.pop();
        return history.peek();
    }

    public void addBookmark(Bookmark bookmark) {
        String url = bookmark.getUrl();
        bookmarks.put(url, bookmark);
        System.out.println("북마크 추가: " + bookmark);
    }

    public List<Bookmark> getBookmarksSortedByName() {
        List<Bookmark> bookmarkList = new ArrayList<>(bookmarks.values());
        bookmarkList.sort(null);
        return bookmarkList;
    }

    public List<Bookmark> getBookmarksSortedByUrl() {
        List<Bookmark> bookmarkList = new ArrayList<>(bookmarks.values());
        bookmarkList.sort(new UrlComparator());
        return bookmarkList;
    }

}
