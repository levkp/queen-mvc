//package queenapp.bootstrap;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Profile({"!test & dev2"})
//@DependsOn("queenData")
//@Component
//public class SeedCollectionsRepositories implements CommandLineRunner {
//    private final QueenData data;
//
//    @Autowired
//    public SeedCollectionsRepositories(QueenData data) {
//        this.data = data;
//    }
//
//    @Override
//    public void run(String... args) {
//        data.seed();
//    }
//}
