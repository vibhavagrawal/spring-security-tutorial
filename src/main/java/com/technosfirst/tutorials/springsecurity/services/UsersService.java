package com.technosfirst.tutorials.springsecurity.services;

import com.technosfirst.tutorials.springsecurity.entity.UserDao;
import com.technosfirst.tutorials.springsecurity.enums.Roles;
import com.technosfirst.tutorials.springsecurity.models.User;
import com.technosfirst.tutorials.springsecurity.repos.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private static final String EXISTING_EMAIL = "tutorials@example.com";

    private static final String ADMIN_EMAIL = "admin@example.com";

    public Optional<User> findByEmail(String email) {
        if (EXISTING_EMAIL.equalsIgnoreCase(email)) {
            var user = User.builder()
                    .id(5L)
                    .email(EXISTING_EMAIL)
                    .password("$2a$12$.64povkWv21Jqc1/0NaPReYXtl5SoD8KULu7wk.PUI3AfWRtoPBSa") //test
                    .role(Roles.ROLE_USER);
            return Optional.of(user.build());
        } else if (ADMIN_EMAIL.equalsIgnoreCase(email)) {
            var user = User.builder()
                    .id(10L)
                    .email(ADMIN_EMAIL)
                    .password("$2a$12$.64povkWv21Jqc1/0NaPReYXtl5SoD8KULu7wk.PUI3AfWRtoPBSa") //test
                    .role(Roles.ROLE_ADMIN);
            return Optional.of(user.build());
        }
        return Optional.empty();

    }

    public Optional<User> findByEmail2(String email) {
        Optional<UserDao> userDao = usersRepository.findByEmail(email);
        return userDao.map(this::convertUserDaoToUser);
    }

    private User convertUserDaoToUser(UserDao userDao) {
        return User.builder()
                .id(userDao.getId())
                .email(userDao.getEmail())
                .password(userDao.getPassword())
                .role(userDao.getRole())
                .build();
    }
}
