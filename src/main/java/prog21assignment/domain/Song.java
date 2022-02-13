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
    @Access(value = AccessType.PROPERTY)
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

        System.out.println("PUBLIC SONG CONSTRUCTOR INVOKED");
    }

    protected Song() {
        System.out.println("PROTECTED SONG CONSTRUCTOR INVOKED");
    }

    public void addConcert(Concert... c) {
        playedAt.addAll(List.of(c));
    }

    public String getTitle() {
        System.out.println("SETTING TITLE FOR SONG");
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
        System.out.println("SETTING GENRES");
        this.genres = genres;
        genres.forEach(g -> genreOrdinals.add(g.ordinal()));
    }

    public void setGenreOrdinals(List<Integer> genreOrdinals) {
        System.out.println("SETTING GENRE ORDINALS");
        this.genreOrdinals = genreOrdinals;

        System.out.println(this.genreOrdinals.toString());

        this.genres = genreOrdinals.stream()
                .map(o -> Genre.values()[o])
                .collect(Collectors.toList());
    }

    public List<Integer> getGenreOrdinals() {
        return genreOrdinals;
    }

    public void setFinishedRecording(YearMonth finishedRecording) {
        this.finishedRecording = finishedRecording;
    }

    public void setAlbum(Album album) {
        System.out.println("SETTING ALBUM");
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
