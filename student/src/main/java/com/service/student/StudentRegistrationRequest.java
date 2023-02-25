package com.service.student;

public record StudentRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
