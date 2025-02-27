package com.mishra.journal.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
@Data
@Builder
public class User {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String username;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntry> journalEntries;
    private List<String> roles;
}
