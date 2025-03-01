package com.mishra.journal.cache;

import com.mishra.journal.entity.CacheConfig;
import com.mishra.journal.repository.CacheConfigRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class JournalAppCache {

    @Autowired
    private CacheConfigRepository cacheConfigRepository;

    private Map<String,String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<CacheConfig> all = cacheConfigRepository.findAll();
        for(CacheConfig cacheConfig: all)
            appCache.put(cacheConfig.getKey(),cacheConfig.getValue());


    }
}
