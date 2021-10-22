package prog21assignment.domain;

import java.time.LocalDate;
import java.util.*;

@SuppressWarnings("unused")
public class Album extends QueenEntity {
    private String title;
    private LocalDate release;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public String getGenresAsString() {
        HashSet<Genre> genres = new HashSet<>();
        songs.forEach(s -> genres.addAll(s.getGenres()));

        StringJoiner sj = new StringJoiner(", ");
        genres.forEach(g -> sj.add(g.readable()));

        return sj.toString();
    }

    // Remove all blanks and de-capitalise the first character
    public String titleToCoverPath() {
        String s = title.replace(" ", "").toLowerCase();
        return s;
    }

}
