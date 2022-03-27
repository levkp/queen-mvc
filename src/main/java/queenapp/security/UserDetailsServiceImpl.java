package queenapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import queenapp.domain.QueenUser;
import queenapp.exception.EntityNotFoundException;
import queenapp.service.QueenUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final QueenUserService service;

    @Autowired
    public UserDetailsServiceImpl(QueenUserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            QueenUser user = service.findByUsername(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.isAdmin() ? "ROLE_ADMIN" : "ROLE_STANDARD"));
            return new User(user.getUsername(), user.getSecret(), authorities);
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }
}
