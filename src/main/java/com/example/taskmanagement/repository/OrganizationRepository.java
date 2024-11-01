package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
