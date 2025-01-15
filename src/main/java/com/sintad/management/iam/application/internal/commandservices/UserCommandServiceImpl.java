package com.sintad.management.iam.application.internal.commandservices;

import com.sintad.management.iam.domain.model.aggregates.User;
import com.sintad.management.iam.domain.model.commands.SignInCommand;
import com.sintad.management.iam.domain.model.commands.SignUpCommand;
import com.sintad.management.iam.domain.services.UserCommandService;
import com.sintad.management.iam.infrastructure.authorization.sfs.services.JwtService;
import com.sintad.management.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserCommandServiceImpl(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Optional<User> handle(SignUpCommand command) {
        return Optional.empty();
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        return Optional.empty();
    }
}
