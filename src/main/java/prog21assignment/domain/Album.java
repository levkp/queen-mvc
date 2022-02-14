package prog21assignment.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table
public class Album extends QueenEntity {
    @Column(length = 30, nullable = false, unique = true)
    private String title;

    @Column(length = 5000)
    private String description;

    @Column(nullable = false)
    private LocalDate release;

    @OneToMany(mappedBy = "album", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private final List<Song> songs = new ArrayList<>();

    protected Album() { }

    public Album(String title, LocalDate release) {
        this.title = title;
        this.release = release;
    }

    public Album(String title, LocalDate release, String description) {
        this(title, release);
        this.description = description;
    }

    public void addSong(Song s) {
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
//        songs.forEach(s -> genres.addAll(s.getGenres()));

        StringJoiner sj = new StringJoiner(", ");
        genres.forEach(g -> sj.add(g.readable()));

        return sj.toString();
    }

    // Todo: this is too complicated for what it is, replace length with Duration in Song?
    public double length() {
        int minutes = 0;
        double seconds = 0;
        double result;

        for (Song s : songs) {
            double fraction = s.getLength() % 1;
            seconds += fraction;
            minutes += (int)(s.getLength() - fraction);
        }

        result = minutes + seconds / 60;

        return (double) Math.round(result * 100) / 100;
    }

    // Todo: remove this somehow
    // Remove all blanks and de-capitalise the first character
    public String titleToCoverPath() {
        return title.replace(" ", "").toLowerCase();
    }

//    public static Album fromDto(AlbumDTO dto) {
//        Album a = new Album(dto.getTitle(), dto.getParsedRelease(), dto.getDescription());
//
//
//        return a;
//    }
}
