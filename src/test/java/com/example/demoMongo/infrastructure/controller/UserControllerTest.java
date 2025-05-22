package com.example.demoMongo.infrastructure.controller;

import com.example.demoMongo.domain.User;
import com.example.demoMongo.domain.UserService;
import com.example.demoMongo.exception.NotFoundException;
import com.example.demoMongo.infrastructure.dto.UserDto;
import com.example.demoMongo.infrastructure.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserController userController;

    @DisplayName("findById devuelve el usuario cuando existe")
    @Test
    void findByIdReturnsUserWhenExists() throws NotFoundException {
        User user = User.builder()
                .id("1")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .build();
        UserDto userDto = UserDto.builder()
                .id("1")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .build();
        when(userService.findById("1")).thenReturn(user);
        when(userMapper.userToUserDTO(user)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.findById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @DisplayName("findById lanza NotFoundException cuando el usuario no existe")
    @Test
    void findByIdThrowsNotFoundExceptionWhenUserDoesNotExist() throws NotFoundException {
        when(userService.findById("2")).thenThrow(new NotFoundException("No encontrado"));

        assertThrows(NotFoundException.class, () -> userController.findById("2"));
    }

    @DisplayName("findAll devuelve lista de usuarios cuando existen usuarios")
    @Test
    void findAllReturnsListOfUsers() {
        User user1 = User.builder()
                .id("1")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .build();
        UserDto userDto1 = UserDto.builder()
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
        UserDto userDto2 = UserDto.builder()
                .id("2")
                .firstname("firstname2")
                .lastname("lastname2")
                .email("email2")
                .build();
        when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.userToUserDTO(user1)).thenReturn(userDto1);
        when(userMapper.userToUserDTO(user2)).thenReturn(userDto2);

        ResponseEntity<List<UserDto>> response = userController.findAll();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(List.of(userDto1, userDto2), response.getBody());
    }

    @DisplayName("findAll devuelve lista vacía cuando no hay usuarios")
    @Test
    void findAllReturnsEmptyListWhenNoUsers() {
        when(userService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<UserDto>> response = userController.findAll();

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isEmpty());
    }

    @DisplayName("update actualiza y devuelve el usuario actualizado")
    @Test
    void updateReturnsUpdatedUser() {
        UserDto inputDto = UserDto.builder()
                .id("1")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .build();
        User user = User.builder()
                .id("1")
                .firstname("firstname")
                .lastname("lastname")
                .email("email")
                .build();
        User updatedUser = User.builder()
                .id("1")
                .firstname("firstname2")
                .lastname("lastname2")
                .email("email")
                .build();
        UserDto updatedDto = UserDto.builder()
                .id("1")
                .firstname("firstname2")
                .lastname("lastname2")
                .email("email")
                .build();
        when(userMapper.userDTOToUser(inputDto)).thenReturn(user);
        when(userService.update(user)).thenReturn(updatedUser);
        when(userMapper.userToUserDTO(updatedUser)).thenReturn(updatedDto);

        ResponseEntity<UserDto> response = userController.update(inputDto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(updatedDto, response.getBody());
    }

    @DisplayName("delete elimina el usuario y retorna No Content")
    @Test
    void deleteRemovesUserAndReturnsNoContent() {
        doNothing().when(userService).delete("1");

        ResponseEntity<Void> response = userController.delete("1");

        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}