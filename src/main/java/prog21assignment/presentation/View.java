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

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
public class View {

    private boolean quit;
    private final Scanner sc;
    private final SongService songService;
    private final AlbumService albumService;
    private final ConcertService concertService;
    private final GsonBuilder gb;

    @Autowired
    public View(SongService songService, AlbumService albumService, ConcertService concertService) {
        quit = false;
        sc = new Scanner(System.in);
        gb = new GsonBuilder();
        gb.setPrettyPrinting();
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

    public void handleChoice(int choice) {

        switch (choice) {
            case 0:
                quit = true;
                break;
            case 1:
                songService.getAllSongs().forEach(System.out::println);
                break;
            case 2:
                System.out.print("\nChoose a genre: ");
                listGenres();
                int genreChoice = sc.nextInt();
                System.out.println("Songs that contain the genre of your choice: ");
                List<Song> result = filterSongsOnGenre(choice);
                result.forEach(System.out::println);
//                System.out.println("Saving result to songs.json...");
//                saveToJson(result, "songs");
                break;
            case 3:
                concertService.getAllConcerts().forEach(System.out::println);
                break;
            case 4:
                System.out.println("\nEnter minimum attendance, or leave empty: ");
                int minAttendance = 0;
                sc.nextLine(); // Clearing the scanner
                String input = sc.nextLine();
                if (!input.equals(""))
                    minAttendance = Integer.parseInt(input);

                System.out.print("\nEnter the location of the concert (or a part of it), or leave empty: ");
                String location = sc.nextLine();

                filterConcerts(minAttendance, location).forEach(System.out::println);
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
        try {
            FileWriter fw = new FileWriter(filename + ".json");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Saving result to json failed.");
        }
    }
}
