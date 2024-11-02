package com.example.taskmanagement.dto.response;

import com.example.taskmanagement.model.Address;
import com.example.taskmanagement.model.PhoneNumber;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationResponse {

    private Long id;

    private String organizationName;

    private Address address;


}
