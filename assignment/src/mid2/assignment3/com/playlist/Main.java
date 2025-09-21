package mid2.assignment3.com.playlist;

import mid2.assignment3.com.playlist.domain.Song;
import mid2.assignment3.com.playlist.service.PlaylistManager;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // 1. 플레이리스트 매니저 생성
        PlaylistManager playlistManager = new PlaylistManager();

        // 2. 곡 추가
        Song song1 = new Song("S001", "Butter", "BTS");
        Song song2 = new Song("S002", "Dynamite", "BTS");
        Song song3 = new Song("S003", "Hype Boy", "NewJeans");
        Song song1_duplicate = new Song("S001", "Butter", "BTS (Remix)"); // ID만 같고 다른 정보는 다름

        System.out.println("== 곡 추가 ==");
        playlistManager.addSong(song1);
        playlistManager.addSong(song2);
        playlistManager.addSong(song3);

        // 3. 중복 곡 추가 시도 (실패해야 함)
        System.out.println("\n== 중복 곡 추가 시도 ==");
        playlistManager.addSong(song1_duplicate);

        // 4. 삭제 및 확인
        System.out.println("\n== 곡 삭제 ==");
        playlistManager.removeSong(song2);

        // 5. 최종 플레이리스트 확인
        System.out.println("\n== 최종 플레이리스트 ==");
        Set<Song> songs = playlistManager.getSongs();
        for (Song song : songs) {
            System.out.println(song);
        }
    }
}
