package com.sintad.management.iam.domain.model.commands;

public record SignInCommand(
        String email,
        String password
) {
}
