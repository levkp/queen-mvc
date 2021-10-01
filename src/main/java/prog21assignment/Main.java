//package prog21assignment;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import prog21assignment.domain.Concert;
//import prog21assignment.domain.Genre;
//import prog21assignment.domain.Song;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        boolean quit = false;
//        Scanner sc = new Scanner(System.in);
//
//        try {
//            do {
//                printMenu();
//
//                switch(sc.nextInt()) {
//                    case 0:
//                        quit = true;
//                        break;
//                    case 1:
//                        DataFactory.songs.forEach(System.out::println);
//                        writeToJson(DataFactory.songs, "songs");
//                        System.out.println("Data is saved to songs.json...");
//                        break;
//                    case 2:
//                        System.out.print("\nChoose a genre: ");
//                        listGenres();
//                        int choice = sc.nextInt();
//                        System.out.println("Songs that contain the genre of your choice: ");
//                        filterSongsOnGenre(choice).forEach(System.out::println);
//                        break;
//                    case 3:
//                        DataFactory.concerts.forEach(System.out::println);
//                        break;
//                    case 4:
////                        System.out.print("Enter a minimum attendance (e.g. 1300) or leave empty: ");
////                        sc.nextLine(); // Clearing scanner
////                        String minAttendanceInput = sc.nextLine();
////                        int minAttendance = 0;
////                        if (!minAttendanceInput.equals("")) {
////                            System.out.println("");
////                            minAttendance = Integer.parseInt(minAttendanceInput);
////                        }
////                        System.out.println(minAttendance);
//
////                        System.out.print("\nChoose a genre: ");
////                        Optional<Integer> genreOrdinal = Optional.of(sc.nextInt());
////                        System.out.println(genreOrdinal);
////                        System.out.println("Concerts with a minimum of " + minAttendance + " and songs with the genre" +
////                                " of your choice:");
////                        filterConcerts(minAttendance, genreOrdinal).forEach(System.out::println);
//                        break;
//                }
//
//
//                System.out.println();
//
//            } while(!quit);
//
//        } catch (InputMismatchException ime) {
//            ime.printStackTrace();
//        }
//
//        sc.close();
//    }
//
//    // Todo: try-catch here, side effects in switch
//    public static List<Song> filterSongsOnGenre(int choice) {
//        return DataFactory.songs.stream()
//                .filter(s -> s.getGenres().stream()
//                        .map(Enum::ordinal)
//                        .collect(Collectors.toList())
//                        .contains(choice))
//                .collect(Collectors.toList());
//    }
//
//    public static void listGenres() {
//        Arrays.stream(Genre.values()).forEach(g -> System.out.print(g.ordinal() + ": " + g.readable() + " "));
//    }
//
//    public static List<Concert> filterConcerts(int minAttendance, int genreOrdinal) {
//        Genre g = Genre.values()[genreOrdinal];
//        return DataFactory.concerts.stream()
//                .filter(c -> c.getAttendance() >= minAttendance)
//                .filter(c -> c.getPlayedSongs().stream().anyMatch(s -> s.getGenres().contains(g)))
//                .collect(Collectors.toList());
//    }
//
//    public static void writeToJson(List<Song> data, String fileName) {
////Unable to make field private final int java.time.YearMonth.year accessible: module java.base does not "opens java.time" to unnamed module @5d20b857
////        Type dataType = new TypeToken<List<>>
//
//
//        Gson jsonData = new GsonBuilder().setPrettyPrinting().create();
//        String s = jsonData.toJson(data);
//        try {
//            new FileWriter(fileName + ".json").write(s);
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
//
//    public static void printMenu() {
//        System.out.println("What would you like to do?");
//        System.out.println("────────────────────────────────────────────────");
//        System.out.println("0) Quit");
//        System.out.println("1) Show all songs");
//        System.out.println("2) Show all songs of genre");
//        System.out.println("3) Show all concerts");
//        System.out.println("4) Show concerts with attendance and/or song");
//        System.out.println("────────────────────────────────────────────────");
//        System.out.print("Your input: ");
//    }
//}
