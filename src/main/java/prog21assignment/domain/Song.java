package prog21assignment.domain;

import prog21assignment.util.YearMonthDateAttributeConverter;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Entity
@Table(name = "song")
@SuppressWarnings("unused")
public class Song extends QueenEntity {
    @Column(length = 30, nullable = false, unique = true)
    private String title;

    // Todo: this really should be duration
    @Column(nullable = false)
    private double length;


    /*
    @ManyToMany
    @JoinTable(name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))

     */
    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @ElementCollection(targetClass = Genre.class)
    @JoinTable(name = "song_genre", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.ORDINAL)
    */
    private transient List<Genre> genres = new ArrayList<>();


    @ElementCollection(targetClass = Integer.class)
    @JoinTable(name = "song_genre", joinColumns = @JoinColumn(name = "id"))
    private List<Integer> genreOrdinals = new ArrayList<>();

    @Column(name = "finished_recording", nullable = false)
    @Convert(converter = YearMonthDateAttributeConverter.class)
    private YearMonth finishedRecording;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;

    // Todo: add this attribute to Hibernate
    private final transient List<Concert> playedAt = new ArrayList<>();

    public Song(String title, double length, List<Genre> genres, YearMonth finishedRecording, Album album) {
        this.title = title;
        this.length = length;
        setGenres(genres);
        this.finishedRecording = finishedRecording;
        this.album = album;
    }

    protected Song() { }

    public void addConcert(Concert... c) {
        playedAt.addAll(List.of(c));
    }

    public String getTitle() {
        return title;
    }

    public double getLength() {
        return length;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public YearMonth getFinishedRecording() {
        return finishedRecording;
    }

    public Album getAlbum() {
        return album;
    }

    public List<Concert> getConcerts() {
        return playedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
        genres.forEach(g -> genreOrdinals.add(g.ordinal()));
    }

    public void setGenreOrdinals(List<Integer> genreOrdinals) {
        this.genreOrdinals = genreOrdinals;
        this.genres = genreOrdinals.stream()
                .map(o -> Genre.values()[o])
                .collect(Collectors.toList());
    }

    public void setFinishedRecording(YearMonth finishedRecording) {
        this.finishedRecording = finishedRecording;
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
