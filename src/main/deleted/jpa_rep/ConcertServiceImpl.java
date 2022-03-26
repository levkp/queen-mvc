//package prog21assignment.service.jpa_rep;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//import prog21assignment.domain.Concert;
//import prog21assignment.repository.QueenEntityJpaRepository;
//import prog21assignment.service.QueenEntityService;
//
//import java.util.List;
//
//@Profile("jpa_rep")
//@Service
//public class ConcertServiceImpl implements QueenEntityService<Concert> {
//    private final QueenEntityJpaRepository<Concert> repository;
//
//    @Autowired
//    public ConcertServiceImpl(QueenEntityJpaRepository<Concert> repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public Concert create(Concert c) {
//        return repository.save(c);
//    }
//
//    @Override
//    public List<Concert> read() {
//        return repository.findAll();
//    }
//
//    @Override
//    public Concert findById(int id) {
//        return repository.findById(id).orElse(null);
//    }
//}
