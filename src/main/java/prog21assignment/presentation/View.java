package prog21assignment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Concert;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;
import prog21assignment.service.AlbumService;
import prog21assignment.service.ConcertService;
import prog21assignment.service.SongService;

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

    @Autowired
    public View(SongService songService, AlbumService albumService, ConcertService concertService) {
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
                filterSongsOnGenre(choice).forEach(System.out::println);
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

                System.out.print("\nChoose a genre, or leave empty: ");
                listGenres();
                input = sc.nextLine();
                if (!input.equals("")) {
                    genreChoice = Integer.parseInt(input);
                } else {
                    genreChoice = -1;
                }
//                filterConcerts(minAttendance, genreChoice);
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

    /*
    public List<Concert> filterConcerts(int minAttendance, int genreOrdinal) {

        if (genreOrdinal )

        Genre g = Genre.values()[genreOrdinal];
        return DataFactory.concerts.stream()
            .filter(c -> c.getAttendance() >= minAttendance)
            .filter(c -> c.getPlayedSongs().stream().anyMatch(s -> s.getGenres().contains(g)))
            .collect(Collectors.toList());
    }

     */

    public static void listGenres() {
        Arrays.stream(Genre.values()).forEach(g -> System.out.print(g.ordinal() + ": " + g.readable() + " "));
    }
}
