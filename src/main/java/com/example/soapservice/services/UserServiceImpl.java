package com.example.soapservice.services;

import com.example.soapservice.entities.User;
import com.example.soapservice.exceptions.ResourceNotFoundException;
import com.example.soapservice.repositories.UserRepository;
import com.example.soapservice.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsersNoRoles() {
        return userRepository.findAllNoRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findById(login);
    }

    @Override
    @Transactional
    public void deleteUserByLogin(String login) {
        userRepository.deleteById(login);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        User user = userRepository.findById(updatedUser.getLogin()).orElseThrow(() -> new ResourceNotFoundException("User not found, login: " + updatedUser.getLogin()));
        user.getRoles().clear();
        user.setRoles(updatedUser.getRoles());
    }
}
