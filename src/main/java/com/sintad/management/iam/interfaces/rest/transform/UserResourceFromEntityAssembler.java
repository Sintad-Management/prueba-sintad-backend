package com.sintad.management.iam.interfaces.rest.transform;

import com.sintad.management.iam.domain.model.aggregates.User;
import com.sintad.management.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getEmail(),
                entity.getName());
    }
}
