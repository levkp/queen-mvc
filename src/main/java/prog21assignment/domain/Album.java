package prog21assignment.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

@SuppressWarnings("unused")
public class Album extends QueenEntity {
    private String title;
    private String description;
    private LocalDate release;
    private final transient List<Song> songs;

    public Album(String title, LocalDate release) {
        this.title = title;
        this.release = release;
        songs = new ArrayList<>();
    }

    public Album(String title, LocalDate release, String description) {
        this(title, release);
        this.description = description;
    }

    public void addSong(Song... s) {
        songs.addAll(List.of(s));
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    public void setDescription(String description) {
        this.description = description;
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

    public Duration length() {
        Duration d;
        double minutes = 0;
        double seconds = 0;

        for (Song s : songs) {
            double fraction = s.getLength() % 1;
            seconds += fraction;
            minutes += s.getLength() - fraction;
        }

        d = Duration.ofMinutes((long)seconds);
        d.plusSeconds((long)seconds);

        return d;
    }

    // Remove all blanks and de-capitalise the first character
    public String titleToCoverPath() {
        return title.replace(" ", "").toLowerCase();
    }

}
