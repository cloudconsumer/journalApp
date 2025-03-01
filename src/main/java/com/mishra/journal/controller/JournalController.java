package com.mishra.journal.controller;

import com.mishra.journal.entity.JournalEntry;
import com.mishra.journal.service.JournalEntryService;
import com.mishra.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String get() {
        return "Hellooooo";
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            List<JournalEntry> journalEntries = userService.getUserByName(username).get().getJournalEntries();
            return new ResponseEntity<>(journalEntries,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No entries for user",HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/new")
    public ResponseEntity<?> createJOurnalEntryForUser(@RequestBody JournalEntry newEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.createEntry(newEntry,username);
            return new ResponseEntity<>("journal created for user ",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update/{myId}")
    public ResponseEntity<?> update(@PathVariable ObjectId myId,
                                    @RequestBody JournalEntry newEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.updateEntry(username,myId,newEntry);
            return new ResponseEntity<>(newEntry,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Requested content unavailable",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{myId}")
    public ResponseEntity<?> delete(@PathVariable ObjectId myId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.deleteEntry(username,myId);
            return new ResponseEntity<>("Resource deleted",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Requested content unavailable",HttpStatus.NOT_FOUND);
        }
    }
}
