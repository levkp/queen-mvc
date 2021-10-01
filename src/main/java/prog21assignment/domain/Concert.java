package prog21assignment.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Concert {

    private final int attendance;
    private final String name;
    private final String location;
    private final LocalDate date;
    private final List<Song> playedSongs;

    public Concert(int attendance, String name, String location, LocalDate date) {
        this.attendance = attendance;
        this.name = name;
        this.location = location;
        this.date = date;
        playedSongs = new ArrayList<>();
    }

    public void addPlayedSong(Song... s) {
        playedSongs.addAll(List.of(s));
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

    public List<Song> getPlayedSongs() {
        return playedSongs;
    }

    //Todo: add played songs
    @Override
    public String toString() {
        return String.format("Name: %-30s │ Location: %-35s │ Attendance: %-5d │ Date: %s │ Played songs: ",
                name, location, attendance, date);
    }
}
