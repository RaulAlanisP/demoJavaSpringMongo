package com.example.demoMongo.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User userEntity);

    Optional<User> findById(String id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    void deleteById(String id);
}
