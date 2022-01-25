package com.authorizationserver.repository;

import com.authorizationserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String name);
    List<User> findByRoles_Name(String name);
}
