//package prog21assignment.service.jpa_rep;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//import prog21assignment.domain.Album;
//import prog21assignment.exceptions.EntityNotFoundException;
//import prog21assignment.repository.QueenEntityJpaRepository;
//import prog21assignment.service.QueenEntityService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Profile("jpa_rep")
//@Service
//public class AlbumServiceImpl implements QueenEntityService<Album> {
//    private final QueenEntityJpaRepository<Album> repository;
//
//    @Autowired
//    public AlbumServiceImpl(QueenEntityJpaRepository<Album> repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public Album create(Album a) {
//        return repository.save(a);
//    }
//
//    @Override
//    public List<Album> read() {
//        return repository.findAll();
//    }
//
//
//    @Override
//    public Album findById(int id) {
//        Optional<Album> o = repository.findById(id);
//
//        if (o.isEmpty()) {
//            throw new EntityNotFoundException();
//        }
//
//        return o.get();
//    }
//}
