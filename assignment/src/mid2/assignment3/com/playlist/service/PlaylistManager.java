package mid2.assignment3.com.playlist.service;

import mid2.assignment3.com.playlist.domain.Song;

import java.util.LinkedHashSet;
import java.util.Set;

public class PlaylistManager {

    //보통은 mp3나 음악 앱에서 재생목록을 만들때 넣은 순서대로 재생되기 때문에 LinkedHashSet 사용
    private final Set<Song> songs = new LinkedHashSet<>();

    public void addSong(Song song) {
        if (songs.contains(song)) {
            System.out.println("오류: 이미 존재하는 곡입니다. (ID: " + song.getId() + ")");
            return;
        }

        System.out.println("곡 '" + song.getTitle() + "' 가 추가되었습니다.");
        songs.add(song);
    }

    public void removeSong(Song song) {
        System.out.println("곡 '" + song.getTitle() + "'가 삭제되었습니다.");
        songs.remove(song);
    }

    public Set<Song> getSongs() {
        return this.songs;
    }
}
