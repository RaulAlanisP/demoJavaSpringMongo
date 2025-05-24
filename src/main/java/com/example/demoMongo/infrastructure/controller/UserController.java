package com.example.demoMongo.infrastructure.controller;

import com.example.demoMongo.domain.User;
import com.example.demoMongo.domain.UserService;
import com.example.demoMongo.exception.NotFoundException;
import com.example.demoMongo.infrastructure.dto.UserDto;
import com.example.demoMongo.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios.
 * Proporciona endpoints para consultar, actualizar y eliminar usuarios.
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return el usuario encontrado en formato UserDto
     * @throws NotFoundException si el usuario no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) throws NotFoundException {
        User user = userService.findById(id);
        UserDto userDTO = userMapper.userToUserDTO(user);

        return ResponseEntity.ok(userDTO);
    }

    /**
     * Obtiene la lista de todos los usuarios.
     *
     * @return lista de usuarios en formato UserDto
     */
    @GetMapping()
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> userDTOS = userService.findAll().stream().map(userMapper::userToUserDTO).toList();

        return ResponseEntity.ok(userDTOS);
    }

    /**
     * Actualiza la información de un usuario.
     *
     * @param userDTO datos del usuario a actualizar
     * @return usuario actualizado en formato UserDto
     */
    @PutMapping()
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User updated = userService.update(user);
        UserDto updatedDTO = userMapper.userToUserDTO(updated);

        return ResponseEntity.ok(updatedDTO);
    }

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador del usuario a eliminar
     * @return respuesta sin contenido si la eliminación fue exitosa
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
