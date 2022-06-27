package com.example.soapservice.services;

import com.example.soapservice.entities.UserEntity;
import com.example.soapservice.soap.users.User;
import com.example.soapservice.exceptions.ResourceNotFoundException;
import com.example.soapservice.repositories.UserRepository;
import com.example.soapservice.soap.users.Userwr;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public static final Function<UserEntity, User> functionEntityToSoap = ue -> {
        User u = new User();
        u.setLogin(ue.getLogin());
        u.setName(ue.getName());
        u.setPassword(ue.getPassword());
        ue.getRoleEntities().stream().map(RoleService.functionEntityToSoap).forEach(re -> u.getRoles().add(re));
        return u;
    };

    public static final Function<User, UserEntity> functionSoapToEntity = u -> {
        UserEntity ue = new UserEntity();
        ue.setLogin(u.getLogin());
        ue.setName(u.getName());
        ue.setPassword(u.getPassword());
        u.getRoles().stream().map(RoleService.functionSoapToEntity).forEach(r -> ue.getRoleEntities().add(r));
        return ue;
    };

    public static final Function<UserEntity, Userwr> functionEntityToSoapwr = ue -> {
        Userwr uwr = new Userwr();
        uwr.setLogin(ue.getLogin());
        uwr.setName(ue.getName());
        uwr.setPassword(ue.getPassword());
        return uwr;
    };

    @Transactional(readOnly = true)
    public List<Userwr> getAllUsers() {
       return userRepository.findAll().stream().map(functionEntityToSoapwr).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return userRepository.findById(login).map(functionEntityToSoap).orElseThrow(() -> new ResourceNotFoundException("User not found, login: " + login));
    }

    @Transactional
    public void deleteUserByLogin(String login) {
        userRepository.deleteById(login);
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(functionSoapToEntity.apply(user));
    }

    @Transactional
    public void updateUser(User updatedUser) {
//        UserEntity userEntity = userRepository.findById(updatedUser.getLogin()).orElseThrow(() -> new ResourceNotFoundException("User not found, login: " + updatedUser.getLogin()));
//        user.getRoles().clear();
//        user.setRoles(updatedUser.getRoles());
    }
}
