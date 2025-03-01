package com.mishra.journal.repository;

import com.mishra.journal.entity.CacheConfig;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheConfigRepository extends MongoRepository<CacheConfig, ObjectId> {
}
