package com.example.demoMongo.application;

import com.example.demoMongo.domain.User;
import com.example.demoMongo.domain.UserRepository;
import com.example.demoMongo.domain.UserService;
import com.example.demoMongo.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User update(User updateUser) {

        User user = userRepository.findById(updateUser.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) throws NotFoundException {

        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
