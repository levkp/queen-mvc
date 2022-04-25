package queenapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import queenapp.domain.QueenUser;
import queenapp.exception.EntityNotFoundException;
import queenapp.repository.QueenUserRepository;

@Service
public class QueenUserServiceImpl implements QueenUserService {
    private final QueenUserRepository repository;

    @Autowired
    public QueenUserServiceImpl(QueenUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public QueenUser create(java.lang.String username, java.lang.String secret, boolean isAdmin) {
        return repository.create(new QueenUser(username, secret, isAdmin));
    }

    public QueenUser findByUsername(java.lang.String username) {
        return repository.findByUsername(username).orElseThrow(
                EntityNotFoundException::new
        );
    }
}
