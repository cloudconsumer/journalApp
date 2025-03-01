package com.mishra.journal.controller;

import com.mishra.journal.entity.User;
import com.mishra.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String get() {
        return "Hellooooo user";
    }


    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody User newUser) {
        try {
            userService.saveUser(newUser);
            return new ResponseEntity<>("User created in database",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong, try again with correct input data",HttpStatus.BAD_REQUEST);
        }
    }
}

