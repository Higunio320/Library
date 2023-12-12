package com.library.backend.api.auth;

import com.library.backend.api.auth.data.AuthenticationRequest;
import com.library.backend.api.auth.data.AuthenticationResponse;
import com.library.backend.api.auth.data.RegisterRequest;
import com.library.backend.api.auth.interfaces.AuthService;
import com.library.backend.config.jwt.JwtServiceImpl;
import com.library.backend.config.jwt.interfaces.JwtService;
import com.library.backend.entities.user.Role;
import com.library.backend.entities.user.User;
import com.library.backend.entities.user.interfaces.UserRepository;
import com.library.backend.utils.exceptions.users.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtServiceImpl;

    private final AuthenticationManager authenticationManager;

    private static final String JWT_RETURN = "Returning JWT token";

    @Override
    public final AuthenticationResponse register(RegisterRequest request) {
        log.info("Creating user for request: {}", request);

        User user = User
                .builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        log.info("Saved user: {}", savedUser);

        String jwtToken = jwtServiceImpl.generateToken(savedUser);

        log.info(JWT_RETURN);
        return  AuthenticationResponse
                .builder()
                .token(jwtToken)
                .isAdmin(savedUser.isAdmin())
                .build();
    }

    @Override
    public final AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Authenticating request: {}", request);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        log.info("Fetching user with username: {}", request.email());

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with email: %s has not been found", request.email())));

        String jwtToken = jwtServiceImpl.generateToken(user);

        log.info(JWT_RETURN);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .isAdmin(user.isAdmin())
                .build();
    }
}
