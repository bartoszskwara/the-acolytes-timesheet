package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.commons.io.IOUtils;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Value("${resource.path}")
    private String RESOURCE_PATH;

    @Value("${resource.avatar.default}")
    private String DEFAULT_AVATAR;

    @Value("${resource.avatar.path}")
    private String AVATAR_PATH;

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

    public byte[] getAvatar(Long userId) {
        User user = this.userRepository.findOne(userId);
        if(user == null) {
            throw new NotFoundException("User not found");
        }

        Path path = Paths.get(RESOURCE_PATH + AVATAR_PATH + "/");
        Optional<Path> avatar;
        try {

            avatar = Files.walk(path)
                    .filter(p -> p.getFileName().toString().equals(user.getAvatar()))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerException("Cannot download the avatar");
        }

        if(!avatar.isPresent()) {
            throw new NotFoundException("Avatar has not been found");
        }

        InputStream in;
        try {
            in = new FileInputStream(Paths.get(RESOURCE_PATH + AVATAR_PATH + "/" + avatar.get().getFileName()).toAbsolutePath().toFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ServerException("Avatar has not been found");
        }

        try {
            byte[] response = IOUtils.toByteArray(in);
            in.close();
            return response;
        } catch (IOException e) {
            throw new ServerException("Cannot download the avatar");
        }
    }

}
