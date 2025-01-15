package com.sintad.management.iam.interfaces.rest.transform;

import com.sintad.management.iam.domain.model.commands.SignInCommand;
import com.sintad.management.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.email(), resource.password());
    }}
