package com.example.taskmanagement.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    String name;
    String surname;
    String username;
    String email;
    Long organizationId; //this will be taken from the jwt,
    // as only admins can create users they should already be authenticated

}
