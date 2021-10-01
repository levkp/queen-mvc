package prog21assignment.domain;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Song {
    private final String title;
    private final double length;
    private final List<Genre> genres;
    private final YearMonth finishedRecording;
    private final Album album;
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

    public double getLength() {
        return length;
    }

    public String getTitle() {
        return title;
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

    @Override
    public String toString() {

        // Todo: replace with reduce()
        StringBuilder songGenres = new StringBuilder();
        genres.forEach(g -> songGenres.append(g.readable()).append(", "));

        return String.format("Title: %-25s │ Length: %d:%d │ Genres: %-35s │ Album: %-20s │ Finished recording: %s",
                title, (int)length, (int)(length % 1 * 10), songGenres, album.getTitle(), finishedRecording.toString());
    }
}
