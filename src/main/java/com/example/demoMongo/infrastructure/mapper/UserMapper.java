package com.example.demoMongo.infrastructure.mapper;

import com.example.demoMongo.domain.User;
import com.example.demoMongo.infrastructure.dto.UserDto;
import com.example.demoMongo.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User userEntityToUser(UserEntity userEntity);

    UserEntity userToUserEntity(User user);

    UserDto userToUserDTO(User user);

    User userDTOToUser(UserDto userDTO);
}
