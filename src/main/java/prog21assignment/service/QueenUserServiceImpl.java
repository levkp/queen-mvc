package prog21assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prog21assignment.domain.QueenUser;
import prog21assignment.exception.EntityNotFoundException;
import prog21assignment.repository.QueenUserRepository;

import java.util.Optional;

@Service
public class QueenUserServiceImpl implements QueenUserService {
    private final QueenUserRepository repository;

    @Autowired
    public QueenUserServiceImpl(QueenUserRepository repository) {
        this.repository = repository;
    }

    public QueenUser findByUsername(String username) {
        Optional<QueenUser> o = repository.findByUsername(username);

        if (o.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return o.get();
    }
}
