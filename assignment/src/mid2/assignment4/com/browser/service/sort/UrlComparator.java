package mid2.assignment4.com.browser.service.sort;

import mid2.assignment4.com.browser.domain.Bookmark;

import java.util.Comparator;

public class UrlComparator implements Comparator<Bookmark> {
    @Override
    public int compare(Bookmark o1, Bookmark o2) {
        return o1.getUrl().compareTo(o2.getUrl());
    }
}
