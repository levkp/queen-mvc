package prog21assignment.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "concert")
@SuppressWarnings("unused")
public class Concert extends QueenEntity {
    @Column
    private int attendance;

    @Column(length = 60, nullable = false, unique = true)
    private String name;

    @Column
    private String location;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "concert_playlist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "concert_id")
    )
    private final List<Song> songs = new ArrayList<>();

    public Concert(int attendance, String name, String location, LocalDate date) {
        this.attendance = attendance;
        this.name = name;
        this.location = location;
        this.date = date;
    }

    protected Concert() { }

    public int getAttendance() {
        return attendance;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addSongs(Song... s) {
        songs.addAll(List.of(s));
    }

    @Override
    public String toString() {
        return String.format("Name: %-30s │ Location: %-35s │ Attendance: %-5d │ Date: %s  ",
                name, location, attendance, date);
    }
}
