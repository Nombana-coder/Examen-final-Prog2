package org.example.examenfinalprog2.model;

public record User(
        String id,
        String ref,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
