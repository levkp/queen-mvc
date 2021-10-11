package prog21assignment.presentation;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Concert;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;
import prog21assignment.service.AlbumService;
import prog21assignment.service.ConcertService;
import prog21assignment.service.SongService;
import prog21assignment.util.LocalDateSerializer;
import prog21assignment.util.YearMonthSerializer;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Todo: implement MVP later
@SuppressWarnings({"unused", "FieldCanBeLocal"})
@Component
public class ConsoleAppView {

    private boolean quit;
    private final Scanner sc;
    private final SongService songService;
    private final AlbumService albumService;
    private final ConcertService concertService;

    private static final GsonBuilder gb = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(YearMonth.class, new YearMonthSerializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer());

    @Autowired
    public ConsoleAppView(SongService songService, AlbumService albumService, ConcertService concertService) {
        quit = false;
        sc = new Scanner(System.in);
        this.songService = songService;
        this.albumService = albumService;
        this.concertService = concertService;
    }

    public void showMenu() {
        while(!quit) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("────────────────────────────────────────────────");
            System.out.println("0) Quit");
            System.out.println("1) Show all songs");
            System.out.println("2) Show all songs of genre");
            System.out.println("3) Show all concerts");
            System.out.println("4) Show concerts with attendance and/or song");
            System.out.println("────────────────────────────────────────────────");
            System.out.print("Your input: ");

            try {
                handleChoice(sc.nextInt());
            } catch (InputMismatchException ime) {
                ime.printStackTrace();
            }
        }

        sc.close();
    }

    // Todo: replace with enhanced switch
    public void handleChoice(int choice) {
        switch (choice) {
            case 0 -> quit = true;

            case 1 -> {
                List<Song> result = songService.getAllSongs();
                result.forEach(System.out::println);
                saveToJson(result, "songs");
            }
            case 2 -> {
                System.out.print("\nChoose a genre: ");
                listGenres();
                List<Song> result = filterSongsOnGenre(sc.nextInt());
                result.forEach(System.out::println);
                saveToJson(result, "songsWithGenreFilter");
            }
            case 3 -> {
                List<Concert> result = concertService.getAllConcerts();
                result.forEach(System.out::println);
                saveToJson(result, "concerts");
            }
            case 4 -> {
                System.out.print("\nEnter minimum attendance, or leave empty: ");
                int minAttendance = 0;
                sc.nextLine(); // Clearing the scanner
                String input = sc.nextLine();
                if (!input.equals("")) {
                    minAttendance = Integer.parseInt(input);
                }
                System.out.print("\nEnter the location of the concert (or a part of it), or leave empty: ");
                filterConcerts(minAttendance, sc.nextLine()).forEach(System.out::println);
            }
        }
    }

    public List<Song> filterSongsOnGenre(int choice) {
        return songService.getAllSongs().stream()
                .filter(s -> s.getGenres().stream()
                        .map(Enum::ordinal)
                        .collect(Collectors.toList())
                        .contains(choice))
                .collect(Collectors.toList());
    }


    public List<Concert> filterConcerts(int minAttendance, String location) {
        return concertService.getAllConcerts().stream()
            .filter(c -> c.getAttendance() >= minAttendance)
            .filter(c -> location == null || c.getLocation().contains(location))
            .collect(Collectors.toList());
    }

    public static void listGenres() {
        Arrays.stream(Genre.values()).forEach(g -> System.out.print(g.ordinal() + ": " + g.readable() + " "));
    }

    public static void saveToJson(List queryResult, String filename) {
        System.out.println("Saving query results...");
        try {
            FileWriter fw = new FileWriter(filename + ".json");
            fw.write(gb.create().toJson(queryResult));
            fw.close();
            System.out.println("Saved query results to " + filename + ".json!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Saving query results failed!");
        }
    }
}
