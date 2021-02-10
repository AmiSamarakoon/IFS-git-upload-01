package com.example.demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestRestAPIs {

    @GetMapping("/api/test/trainer")
    @PreAuthorize("hasRole('ROLE_TRAINER') or hasRole('MANAGER')")
    public String userAccess() {
        return ">>> Trainer Contents!";
    }

    @GetMapping("/api/test/depManager")
    @PreAuthorize("hasRole('DEPMANAGER') or hasRole('MANAGER')")
    public String projectManagementAccess() {
        return ">>> Deputy Manager Board";
    }

    @GetMapping("/api/test/manager")
    @PreAuthorize("hasRole('MANAGER')")
    public String adminAccess() {
        return ">>> Manager Contents";
    }



}
