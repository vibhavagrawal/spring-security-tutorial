package com.technosfirst.tutorials.springsecurity.repos;

import com.technosfirst.tutorials.springsecurity.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserDao, Long> {
    Optional<UserDao> findByEmail(String email);
}
