package com.library.libraryuserservice.controllers;

import com.library.libraryuserservice.models.User;
import com.library.libraryuserservice.services.UserService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/users")
public class UserController {
    final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping( path = "/",
            produces = "application/json"
    )
    public ResponseEntity<List<User>> getAllUser() {
        var list = service.getAllEntries();
        if (!list.isEmpty()) return new ResponseEntity<>(list, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @SneakyThrows
    @GetMapping( path = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        var user = service.getEntryById(id);
        if (user != null) return new ResponseEntity<>(user, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @SneakyThrows
    @GetMapping( path = "/{username}",
            produces = "application/json"
    )
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        var user = service.getUserByUsername(username);
        if (user != null) return new ResponseEntity<>(user, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping( path = "/",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        var result = service.create(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @SneakyThrows
    @DeleteMapping( path = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
