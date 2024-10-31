package com.example.taskmanagement.model;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "granted_user_authority")
public class GrantedUserAuthority implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserAuthority userAuthority;

    @Override
    public String getAuthority() {
        return userAuthority.toString();
    }
}
