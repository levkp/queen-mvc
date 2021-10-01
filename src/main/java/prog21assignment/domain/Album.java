package prog21assignment.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Album {
    private final String title;
    private final LocalDate release;
    private final transient List<Song> songs;

    public Album(String title, LocalDate release) {
        this.title = title;
        this.release = release;
        songs = new ArrayList<>();
    }

    public void addSong(Song... s) {
        songs.addAll(List.of(s));
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getRelease() {
        return release;
    }

    public List<Song> getSongs() {
        return songs;
    }

    // Todo: make toString() pretty
    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", release=" + release +
                ", songs=" + songs +
                '}';
    }
}
