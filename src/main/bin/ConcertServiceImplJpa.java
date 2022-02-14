//package prog21assignment.service.jpa;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//import prog21assignment.domain.Concert;
//import prog21assignment.exceptions.EntityNotFoundException;
//import prog21assignment.repository.QueenEntityRepository;
//import prog21assignment.service.QueenEntityService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Profile("jpa")
//@Service
//public class ConcertServiceImplJpa implements QueenEntityService<Concert> {
//    private final QueenEntityRepository<Concert> repository;
//
//    @Autowired
//    public ConcertServiceImplJpa(QueenEntityRepository<Concert> repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public Concert create(Concert c) {
//        return repository.create(c);
//    }
//
//    @Override
//    public List<Concert> read() {
//        return repository.read();
//    }
//
//    @Override
//    public Concert findById(int id) {
//        Optional<Concert> concert = repository.findById(id);
//
//        if (concert.isEmpty()) {
//            throw new EntityNotFoundException();
//        }
//
//        return concert.get();
//    }
//}
