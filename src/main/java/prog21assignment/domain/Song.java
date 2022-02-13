package prog21assignment.domain;

import prog21assignment.util.YearMonthDateAttributeConverter;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "song")
public class Song extends QueenEntity {
    @Column(length = 30, nullable = false, unique = true)
    private String title;

    // Todo: this really should be duration
    @Column(nullable = false)
    private double length;

    private transient Set<Genre> genres = new HashSet<>();

    @Access(value = AccessType.PROPERTY)
    @ElementCollection(targetClass = Integer.class)
    @JoinTable(name = "song_genre", joinColumns = @JoinColumn(name = "id"))
    private List<Integer> genreOrdinals = new ArrayList<>();

    @Column(name = "finished_recording", nullable = false)
    @Convert(converter = YearMonthDateAttributeConverter.class)
    private YearMonth finishedRecording;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;

    public Song(String title, double length, Set<Genre> genres, YearMonth finishedRecording, Album album) {
        this.title = title;
        this.length = length;
        setGenres(genres);
        this.finishedRecording = finishedRecording;
        this.album = album;
    }

    protected Song() { }

    public String getTitle() {
        return title;
    }

    public double getLength() {
        return length;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public YearMonth getFinishedRecording() {
        return finishedRecording;
    }

    public Album getAlbum() {
            return album;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
        genres.forEach(g -> genreOrdinals.add(g.ordinal()));
    }

    public void setGenreOrdinals(List<Integer> genreOrdinals) {
        this.genreOrdinals = genreOrdinals;
        this.genres = genreOrdinals.stream()
                .map(o -> Genre.values()[o])
                .collect(Collectors.toSet());
    }

    public List<Integer> getGenreOrdinals() {
        return genreOrdinals;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String genresToString() {
        StringJoiner sj = new StringJoiner(", ");
        genres.forEach(g -> sj.add(g.readable()));
        return sj.toString();
    }

    @Override
    public String toString() {
        return String.format("Title: %-25s │ Length: %d:%d │ Genres: %-35s │ Album: %-20s │ Finished recording: %s",
                title, (int)length, (int)(length % 1 * 10), genresToString(), album.getTitle(), finishedRecording.toString());
    }
}
