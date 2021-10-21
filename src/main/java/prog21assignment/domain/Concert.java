package prog21assignment.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Concert extends QueenEntity {
    private int attendance;
    private String name;
    private String location;
    private LocalDate date;
    private final List<Song> songs;

    public Concert(int attendance, String name, String location, LocalDate date) {
        this.attendance = attendance;
        this.name = name;
        this.location = location;
        this.date = date;
        songs = new ArrayList<>();
    }

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
