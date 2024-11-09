package com.technosfirst.tutorials.springsecurity.entity;

import com.technosfirst.tutorials.springsecurity.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Entity
@Data
public class UserDao {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NaturalId
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;
}
