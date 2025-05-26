package com.example.demoMongo.infrastructure.repository;

import com.example.demoMongo.infrastructure.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringUserRepository extends MongoRepository<UserEntity, String> {
    Boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
