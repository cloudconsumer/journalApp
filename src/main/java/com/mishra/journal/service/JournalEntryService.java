package com.mishra.journal.service;

import com.mishra.journal.entity.JournalEntry;
import com.mishra.journal.entity.User;
import com.mishra.journal.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    JournalRepository journalRepository;

    @Autowired
    UserService userService;

    public List<JournalEntry> getAllEntries() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getOneEntry(ObjectId id) {
        return journalRepository.findById(id);
    }

    @Transactional
    public void createEntry(JournalEntry entry, String username) {
        entry.setTime(LocalDateTime.now());
        JournalEntry journalEntry = journalRepository.save(entry);
        User updatedUser = userService.getUserByName(username).get();
        updatedUser.getJournalEntries().add(journalEntry);
//        updatedUser.setUsername(null);
        userService.saveUser(updatedUser);
    }

    public void updateEntry(String username, ObjectId myId, JournalEntry newEntry) {
        JournalEntry oldEntry = getOneEntry(myId).orElse(null);
        newEntry.setId(myId);
        newEntry.setContent((newEntry.getContent() == null || newEntry.getContent().isEmpty()) ? oldEntry.getContent() : newEntry.getContent());
        newEntry.setTitle(newEntry.getTitle().isEmpty() ? oldEntry.getTitle() : newEntry.getTitle());
        newEntry.setTime(LocalDateTime.now());
        journalRepository.save(newEntry);
    }
    @Transactional
    public void deleteEntry (String username, ObjectId myId){
        User updatedUser = userService.getUserByName(username).get();
        updatedUser.getJournalEntries().removeIf(journal->journal.getId().equals(myId));
        userService.saveUser(updatedUser);
        journalRepository.deleteById(myId);
    }
}
