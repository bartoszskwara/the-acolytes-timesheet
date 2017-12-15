package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.AuthUserDetails;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username or email
        User user = userRepository.findOneByEmailOrUsername(username, username);

        if (user != null) {
            return new AuthUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User does not exist");
        }


    }

}