package com.example.demoMongo.domain;

import java.util.List;

public interface UserService {
    User update(User user);

    List<User> findAll();

    User findById(String id);

    void delete(String id);
}
