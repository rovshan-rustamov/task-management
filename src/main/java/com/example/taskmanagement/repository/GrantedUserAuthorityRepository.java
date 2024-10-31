package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.GrantedUserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrantedUserAuthorityRepository extends JpaRepository<GrantedUserAuthority, Long> {
}
