package com.mishra.journal.controller;

import com.mishra.journal.api.response.WeatherResponse;
import com.mishra.journal.entity.User;
import com.mishra.journal.service.UserService;
import com.mishra.journal.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/get")
    public ResponseEntity<User> getOne() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userService.getUserByName(username);
        return user.map(userObject -> new ResponseEntity<>(userObject, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User newUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try{
            userService.updateUser(username,newUser);
            return new ResponseEntity<>(newUser,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/weather")
    public ResponseEntity<String> getWeather() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String city = "Hyderabad";
            WeatherResponse response = weatherService.getCurrentWeather(city);
            String weatherInfo = "";
            if (response != null)
                weatherInfo = String.valueOf(response.getCurrent().getFeelslike());
            return new ResponseEntity<>(String.format("Hi %s, the weather in %s feels like %s", username, city, weatherInfo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

