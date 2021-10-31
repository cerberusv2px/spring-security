package com.example.springsecurity.domain.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springsecurity.domain.model.User;

@Repository
public interface UserRepo  extends JpaRepository<User, Long>{

    public Optional<User> findByUsername(String username);

}
