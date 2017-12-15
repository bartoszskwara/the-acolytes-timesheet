package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.ForbiddenException;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.UserDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.NewUser;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private static final String DEFAULT_AVATAR = "default.png";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UserDto> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(u -> UserDtoBuilder.getInstance()
                        .withId(u.getId())
                        .withName(u.getName())
                        .withRoles(u.getRoles())
                        .setActive(u.isActive())
                        .build());
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findOne(userId);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        return UserDtoBuilder.buildFromEntity(user);
    }

    public User addNewUser(NewUser newUser) {
        if(newUser == null) {
            throw new NotFoundException("No user data found");
        }
        if(userRepository.findOneByEmail(newUser.getEmail()) != null) {
            throw new ForbiddenException("Email already used");
        }
        if(userRepository.findOneByUsername(newUser.getUsername()) != null) {
            throw new ForbiddenException("Username already used");
        }

        User user = new User();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setActive(true);
        user.setAvatar(DEFAULT_AVATAR);

        User saved = userRepository.save(user);
        if(saved != null) {
            return saved;
        }
        else {
            throw new ServerException("User has not been saved due to server problems");
        }
    }

}
