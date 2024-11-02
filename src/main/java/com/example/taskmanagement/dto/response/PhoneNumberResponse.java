package com.example.taskmanagement.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneNumberResponse {
    private Long id;
    private String phoneNumber;
}
