package queenapp.service;

import queenapp.domain.QueenUser;

public interface QueenUserService {
    QueenUser create(String username, String secret, boolean isAdmin);
    QueenUser findByUsername(String username);
}
