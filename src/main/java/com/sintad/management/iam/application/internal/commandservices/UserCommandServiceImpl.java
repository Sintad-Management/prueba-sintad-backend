package com.sintad.management.iam.application.internal.commandservices;

import com.sintad.management.iam.domain.model.aggregates.User;
import com.sintad.management.iam.domain.model.commands.SignInCommand;
import com.sintad.management.iam.domain.model.commands.SignUpCommand;
import com.sintad.management.iam.domain.services.UserCommandService;
import com.sintad.management.iam.infrastructure.authorization.sfs.services.JwtService;
import com.sintad.management.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserCommandServiceImpl(
            UserRepository userRepository,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new RuntimeException("Email ya existe");
        }

        String encodedPassword = passwordEncoder.encode(command.password());

        User user = new User(command.name(), command.email(), encodedPassword);
        userRepository.save(user);

        return Optional.of(user);
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(command.email(), command.password())
            );
        } catch (Exception ex) {
            throw new RuntimeException("Invalid email or password.");
        }

        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new RuntimeException("User not found."));

        String jwtToken = jwtService.generateToken(user);

        return Optional.of(ImmutablePair.of(user, jwtToken));
    }
}
