package queenapp.service;

import queenapp.domain.QueenUser;

public interface QueenUserService {
    QueenUser findByUsername(String username);
}
