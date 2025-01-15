package com.sintad.management.iam.interfaces.rest.resources;

public record SignUpResource(
        String name,
        String email,
        String password
) {
}
