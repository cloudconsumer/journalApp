package com.mishra.journal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cache_config")
@Data
@NoArgsConstructor
public class CacheConfig {
    private String key;
    private String value;
}
