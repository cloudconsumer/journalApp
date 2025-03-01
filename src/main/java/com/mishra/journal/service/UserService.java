package com.mishra.journal.service;

import com.mishra.journal.entity.User;
import com.mishra.journal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User newUser) {
        log.error("error occured wowowowow");
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRoles(List.of("USER"));
        userRepository.save(newUser);
    }

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    public void updateUser(String username, User newUser) {
        User oldUser = getUserByName(username).orElse(null);
        assert oldUser != null;
        newUser.setId(oldUser.getId());
        newUser.setUsername(newUser.getUsername().isEmpty() ?oldUser.getUsername():newUser.getUsername());
        newUser.setPassword(newUser.getPassword().isEmpty() ?oldUser.getPassword():newUser.getPassword());
        newUser.setJournalEntries(newUser.getJournalEntries()==null ?oldUser.getJournalEntries():newUser.getJournalEntries());
        userRepository.save(newUser);
    }
}
