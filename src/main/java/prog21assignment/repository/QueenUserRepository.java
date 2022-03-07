package prog21assignment.repository;

import prog21assignment.domain.QueenUser;

import java.util.Optional;

public interface QueenUserRepository {
    Optional<QueenUser> findByUsername(String username);
}
