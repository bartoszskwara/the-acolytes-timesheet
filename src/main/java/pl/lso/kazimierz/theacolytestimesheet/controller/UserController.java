package pl.lso.kazimierz.theacolytestimesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.UserDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.NewUser;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.service.UserService;

import java.util.HashMap;
import java.util.HashSet;
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

        UserDto userDto = UserDtoBuilder.buildFromEntity(userService.addNewUser(newUser));

        Map<String, Object> response = new HashMap<>();
        response.put("response", "User has been added");
        response.put("user", userDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

}
