package com.sintad.management.iam.interfaces.rest.resources;

public record SignInResource(
        String email,
        String password
) {
}
