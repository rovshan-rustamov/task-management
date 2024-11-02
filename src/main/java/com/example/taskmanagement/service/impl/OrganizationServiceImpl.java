package com.example.taskmanagement.service.impl;

import com.example.taskmanagement.dto.request.OrganizationRequest;
import com.example.taskmanagement.dto.response.OrganizationResponse;
import com.example.taskmanagement.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public OrganizationResponse getOrganization(Long id) {
        return null;
    }

    @Override
    public OrganizationResponse updateOrganization(Long id, OrganizationRequest request) {
        return null;
    }
}
