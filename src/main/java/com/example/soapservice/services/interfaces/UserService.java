package com.example.soapservice.services.interfaces;

import com.example.soapservice.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    List<User> getAllUsersNoRoles();

    Optional<User> getUserByLogin(String login);

    void deleteUserByLogin(String login);

    void saveUser(User user);

    void updateUser(User user);
}
