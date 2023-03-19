package com.service.student.dto;

public record StudentRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
