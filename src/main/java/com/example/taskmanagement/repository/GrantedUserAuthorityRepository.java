package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.GrantedUserAuthority;
import com.example.taskmanagement.model.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrantedUserAuthorityRepository extends JpaRepository<GrantedUserAuthority, Long> {
    Optional<GrantedUserAuthority> findByUserAuthority(UserAuthority authority);
}
