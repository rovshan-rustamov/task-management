package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.request.OrganizationRequest;
import com.example.taskmanagement.dto.response.OrganizationResponse;

public interface OrganizationService {
    OrganizationResponse getOrganization(Long id);

    OrganizationResponse updateOrganization(Long id, OrganizationRequest request);
}
