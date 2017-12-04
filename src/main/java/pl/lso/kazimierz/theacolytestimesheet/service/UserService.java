package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.UserDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
