package prog21assignment.service;

import prog21assignment.domain.QueenUser;

public interface QueenUserService {
    QueenUser findByUsername(String username);
}
