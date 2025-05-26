package com.example.demoMongo.application;

import com.example.demoMongo.domain.User;
import com.example.demoMongo.domain.UserRepository;
import com.example.demoMongo.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void findByIdReturnsUserWhenExists() {
        User user = User.builder()
                .id("1")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .build();
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        User result = userService.findById("1");

        Assertions.assertEquals(user, result);
    }

    @Test
    void findByIdThrowsNotFoundExceptionWhenUserDoesNotExist() {
        when(userRepository.findById("2")).thenReturn(Optional.empty());

        Assertions.assertThrows(
                NotFoundException.class,
                () -> userService.findById("2")
        );
    }

    @Test
    void findAllReturnsListOfUsers() {
        User user1 = User.builder()
                .id("1")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .build();
        User user2 = User.builder()
                .id("2")
                .firstname("firstname2")
                .lastname("lastname2")
                .email("email2")
                .build();
        List<User> users = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        Assertions.assertEquals(users, result);
    }

    @Test
    void findAllReturnsEmptyListWhenNoUsers() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.findAll();

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void updateReturnsUpdatedUserWhenUserExists() {
        User updateUser = User.builder()
                .id("1")
                .firstname("firstname2")
                .lastname("lastname2")
                .email("email")
                .build();
        User existingUser = User.builder()
                .id("1")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .build();

        when(userRepository.findById("1")).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updateUser);

        User result = userService.update(updateUser);

        Assertions.assertEquals(updateUser, result);
    }

    @Test
    void updateThrowsNotFoundExceptionWhenUserDoesNotExist() {
        User updateUser = User.builder()
                .id("1")
                .firstname("firstname2")
                .lastname("lastname2")
                .email("email")
                .build();
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        Assertions.assertThrows(
                NotFoundException.class,
                () -> userService.update(updateUser)
        );
    }

    @Test
    void deleteCallsRepositoryDeleteById() {
        userService.delete("1");
        org.mockito.Mockito.verify(userRepository).deleteById("1");
    }
}