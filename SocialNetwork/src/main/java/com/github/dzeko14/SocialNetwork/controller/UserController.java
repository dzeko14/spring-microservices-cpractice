package com.github.dzeko14.SocialNetwork.controller;

import com.github.dzeko14.SocialNetwork.exception.InvalidEntityFieldException;
import com.github.dzeko14.SocialNetwork.model.User;
import com.github.dzeko14.SocialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
        } catch (InvalidEntityFieldException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id,
                         @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }
}
