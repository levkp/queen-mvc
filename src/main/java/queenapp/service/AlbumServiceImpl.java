package queenapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import queenapp.domain.Album;
import queenapp.domain.QueenUser;
import queenapp.exception.EntityNotFoundException;
import queenapp.repository.QueenEntityRepository;

import java.util.List;

@Service
public class AlbumServiceImpl implements QueenEntityService<Album> {
    private final QueenEntityRepository<Album> repository;
    private final QueenUserService userService;

    @Autowired
    public AlbumServiceImpl(QueenEntityRepository<Album> repository, QueenUserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public Album create(Album a) {
        return repository.create(a);
    }

    // Todo: remove exception
    @Override
    public List<Album> read() {
        return repository.read();
    }


    @Override
    public Album findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find album with id " + id));
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void updateOwner(Album a, String username) {
        QueenUser newOwner = userService.findByUsername(username);
        a.setOwner(newOwner);
        repository.update(a);
        // Todo should I update each song too?
    }
}