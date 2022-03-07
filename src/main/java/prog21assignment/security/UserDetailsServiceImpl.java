package prog21assignment.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prog21assignment.domain.QueenUser;
import prog21assignment.exception.EntityNotFoundException;
import prog21assignment.service.QueenUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final QueenUserService service;

    public UserDetailsServiceImpl(QueenUserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            QueenUser qu = service.findByUsername(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(qu.getUsername(), qu.getSecret(), authorities);
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }
}
