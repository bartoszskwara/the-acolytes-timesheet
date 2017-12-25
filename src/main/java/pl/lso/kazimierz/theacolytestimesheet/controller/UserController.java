package pl.lso.kazimierz.theacolytestimesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.UserDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.NewUser;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity getUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.getUsers(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity addNewUser(@RequestBody @Validated NewUser newUser) {
        User user = userService.addNewUser(newUser);
        UserDto userDto = UserDtoBuilder.getInstance()
                .withId(user.getId())
                .withName(user.getName())
                .withUsername(user.getUsername())
                .withRoles(user.getRoles())
                .withEmail(user.getEmail())
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("response", "User has been added");
        response.put("user", userDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/avatar/{userId}")
    public ResponseEntity getAvatar(@PathVariable("userId") Long userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(this.userService.getAvatar(userId), headers, HttpStatus.OK);
    }

    @GetMapping("/ranking")
    public ResponseEntity getUsersRanking(Pageable pageable) {
        return new ResponseEntity(userService.getUsersRanking(pageable), HttpStatus.OK);
    }

}
