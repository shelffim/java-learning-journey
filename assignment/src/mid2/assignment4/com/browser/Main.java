package mid2.assignment4.com.browser;

import mid2.assignment4.com.browser.domain.Bookmark;
import mid2.assignment4.com.browser.domain.WebPage;
import mid2.assignment4.com.browser.service.BrowserManager;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 1. 브라우저 매니저 생성 (의존관계 주입)
        BrowserManager browser = new BrowserManager(new ArrayDeque<>(), new HashMap<>());

        // 2. 페이지 방문 및 뒤로 가기 테스트
        System.out.println("== 페이지 방문 및 뒤로 가기 ==");
        browser.visitPage(new WebPage("google.com", "구글"));
        browser.visitPage(new WebPage("naver.com", "네이버"));
        browser.visitPage(new WebPage("github.com", "깃허브"));

        WebPage currentPage = browser.goBack();
        System.out.println("뒤로 가기: " + currentPage);
        currentPage = browser.goBack();
        System.out.println("뒤로 가기: " + currentPage);
        System.out.println("--------------------");

        // 3. 북마크 추가 및 관리 테스트
        System.out.println("== 북마크 관리 ==");
        browser.addBookmark(new Bookmark("google.com", "G - 구글"));
        browser.addBookmark(new Bookmark("github.com", "H - 깃허브"));
        browser.addBookmark(new Bookmark("influen.com", "I - 인프런"));

        // 4. 북마크 정렬 테스트
        System.out.println("\n== 북마크 이름순 정렬 (기본 정렬) ==");
        List<Bookmark> sortedByName = browser.getBookmarksSortedByName();
        for (Bookmark bookmark : sortedByName) {
            System.out.println(bookmark);
        }

        System.out.println("\n== 북마크 URL순 정렬 (외부 정렬) ==");
        List<Bookmark> sortedByUrl = browser.getBookmarksSortedByUrl();
        for (Bookmark bookmark : sortedByUrl) {
            System.out.println(bookmark);
        }
    }
}
