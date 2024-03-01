package com.library.libraryuserservice.controllers;

import com.library.libraryuserservice.models.Role;
import com.library.libraryuserservice.services.RoleService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/roles")
public class RoleController {
    final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping( path = "/",
            produces = "application/json"
    )
    public ResponseEntity<List<Role>> getAllRoles() {
        var list = service.getAllEntries();
        if (!list.isEmpty()) return new ResponseEntity<>(list, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @SneakyThrows
    @GetMapping( path = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        var role = service.getEntryById(id);
        if (role != null) return new ResponseEntity<>(role, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping( path = "/",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        var result = service.create(role);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @SneakyThrows
    @DeleteMapping( path = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
