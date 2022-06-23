package com.example.soapservice.repositories;

import com.example.soapservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u.login, u.name, u.password FROM User u")
    List<User> findAllNoRoles();

}
