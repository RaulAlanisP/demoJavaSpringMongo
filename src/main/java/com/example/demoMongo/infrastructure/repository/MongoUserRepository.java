package com.example.demoMongo.infrastructure.repository;

import com.example.demoMongo.domain.User;
import com.example.demoMongo.domain.UserRepository;
import com.example.demoMongo.infrastructure.entity.UserEntity;
import com.example.demoMongo.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * MongoDB implementation of UserRepository interface.
 */
@Repository
@RequiredArgsConstructor
public class MongoUserRepository implements UserRepository {

    private final SpringUserRepository springUserRepository;
    private final UserMapper userMapper;

    /**
     * Saves a user in the MongoDB database.
     *
     * @param userEntity the user to be saved
     * @return the saved user with any modifications made during the save operation
     */
    @Override
    public User save(User userEntity) {
        UserEntity entity = userMapper.userToUserEntity(userEntity);
        UserEntity saved = springUserRepository.save(entity);
        return userMapper.userEntityToUser(saved);
    }

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the user if found, or empty if not found
     */
    @Override
    public Optional<User> findById(String id) {
        return springUserRepository.findById(id).map(userMapper::userEntityToUser);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list containing all users
     */
    @Override
    public List<User> findAll() {
        return springUserRepository.findAll().stream().map(userMapper::userEntityToUser).toList();
    }

    /**
     * Finds a user by their email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the user if found, or empty if not found
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return springUserRepository.findByEmail(email).map(userMapper::userEntityToUser);
    }

    /**
     * Checks if a user exists with the given email address.
     *
     * @param email the email address to check
     * @return true if a user exists with the given email, false otherwise
     */
    @Override
    public Boolean existsByEmail(String email) {
        return springUserRepository.existsByEmail(email);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    @Override
    public void deleteById(String id) {
        springUserRepository.deleteById(id);
    }
}
