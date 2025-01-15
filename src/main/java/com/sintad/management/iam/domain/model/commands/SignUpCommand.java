package com.sintad.management.iam.domain.model.commands;

public record SignUpCommand(
        String name,
        String email,
        String password
) {
}
