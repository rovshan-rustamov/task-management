package com.example.taskmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrganizationRequest {

    @NotBlank(message = "Organization name is required.")
    private String organizationName;

    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;


    @NotNull(message = "Address is required.")
    private String address;

    // Additional fields if necessary

}
