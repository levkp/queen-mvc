package queenapp.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import queenapp.util.YearMonthDateAttributeConverter;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "song")
public class Song extends QueenEntity {
    // Todo: this really should be Duration
    @Getter @Setter
    @Column(nullable = false)
    private double length;

    @Getter
    private transient Set<Genre> genres = new HashSet<>();

    // Todo: this should be a Set too
    @Getter
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @Access(value = AccessType.PROPERTY)
    @ElementCollection(targetClass = Integer.class)
    @JoinTable(name = "song_genre", joinColumns = @JoinColumn(name = "id"))
    private List<Integer> genreOrdinals = new ArrayList<>();

    @Getter @Setter
    @Column(name = "finished_recording", nullable = false)
    @Convert(converter = YearMonthDateAttributeConverter.class)
    private YearMonth finishedRecording;

    @Getter
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER) @JoinColumn(name = "album_id")
    private Album album;

    public Song() { }

    public Song(String title, double length, Set<Genre> genres, YearMonth finishedRecording, Album album) {
        this(title, length, finishedRecording);
        setGenres(genres);
        this.album = album;
        // Todo
        this.owner = album == null ? null : album.getOwner(); // No problem if null
    }

    public Song(String title, double length, YearMonth finishedRecording) {
        this.title = title;
        this.length = length;
        this.finishedRecording = finishedRecording;
    }

    public void setAlbum(Album album) {
        this.album = album;
        this.owner = album.getOwner();
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
        genres.forEach(g -> genreOrdinals.add(g.ordinal()));
    }

    public void setOwner(QueenUser owner) {
        if (album == null || album.getOwner().equals(owner)) {
            this.owner = owner;
        }
        // Todo: what to do if they are not equal?
    }

    public void setGenreOrdinals(List<Integer> genreOrdinals) {
        this.genreOrdinals = genreOrdinals;
        this.genres = genreOrdinals.stream()
                .map(o -> Genre.values()[o])
                .collect(Collectors.toSet());
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
