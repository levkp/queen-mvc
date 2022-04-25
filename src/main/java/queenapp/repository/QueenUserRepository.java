package queenapp.repository;

import queenapp.domain.QueenUser;

import java.util.Optional;

public interface QueenUserRepository {
    Optional<QueenUser> findByUsername(java.lang.String username);
    QueenUser create(QueenUser user);
}
