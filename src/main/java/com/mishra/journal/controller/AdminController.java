package com.mishra.journal.controller;

import com.mishra.journal.cache.JournalAppCache;
import com.mishra.journal.entity.JournalEntry;
import com.mishra.journal.entity.User;
import com.mishra.journal.service.JournalEntryService;
import com.mishra.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @Autowired
    private JournalAppCache appCache;

    @GetMapping("/all-journals")
    public ResponseEntity<?> getAllJournals() {
        List<JournalEntry> all = journalEntryService.getAllEntries();
        if(all.isEmpty())
            return new ResponseEntity<>("No Content",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> all = userService.getAllUsers();
            if(all.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(all,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No Content ",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/clear-app-cache")
    public ResponseEntity<String> clearCache() {
        try {
            appCache.init();
            return new ResponseEntity<>("application cache cleared ",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
        }
    }
}
