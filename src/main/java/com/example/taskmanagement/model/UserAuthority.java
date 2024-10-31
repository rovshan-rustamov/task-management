package com.example.taskmanagement.model;

public enum UserAuthority {
    SUPER_ADMIN,       // Website-level admin with global privileges
    ORGANIZATION_ADMIN, // Admin within an organization
    USER                 // Regular user within an organization
}
