package prog21assignment.domain;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@SuppressWarnings("unused")
public class Song extends QueenEntity {
    private String title;
    private double length;
    private List<Genre> genres;
    private YearMonth finishedRecording;
    private Album album;
    private final transient List<Concert> playedAt;

    public Song(String title, double length, List<Genre> genres, YearMonth finishedRecording, Album album) {
        this.title = title;
        this.length = length;
        this.genres = genres;
        this.finishedRecording = finishedRecording;
        this.album = album;
        playedAt = new ArrayList<>();
    }

    public void addConcert(Concert... c) {
        playedAt.addAll(List.of(c));
    }

    public void addGenre(Genre... g) {
        genres.addAll(List.of(g));
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
