package pl.lso.kazimierz.theacolytestimesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.service.UserService;

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
        return new ResponseEntity<>(this.userService.getUsers(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }

}
