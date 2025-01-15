package com.sintad.management.iam.interfaces.rest.transform;

import com.sintad.management.iam.domain.model.aggregates.User;
import com.sintad.management.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity , String token){
        return new AuthenticatedUserResource(
                entity.getId(),
                entity.getEmail(),
                token
        );
    }
}
