package com.sintad.management.iam.interfaces.rest.transform;

import com.sintad.management.iam.domain.model.commands.SignUpCommand;
import com.sintad.management.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource (SignUpResource resource){
        return new SignUpCommand(resource.name(), resource.email(), resource.password());
    }
}
