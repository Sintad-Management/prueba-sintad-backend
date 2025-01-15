package com.sintad.management.iam.interfaces.rest.resources;

public record UserResource(
    Long id,
    String email,
    String name
) {
}
