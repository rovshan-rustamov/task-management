package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.request.OrganizationRequest;
import com.example.taskmanagement.dto.response.OrganizationResponse;
import com.example.taskmanagement.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> getOrganizationById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getOrganization(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponse> getOrganizationById(@PathVariable Long id,
                                                                    @RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(organizationService.updateOrganization(id, request));
    }

}
