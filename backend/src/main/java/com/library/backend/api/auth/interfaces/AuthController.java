package com.library.backend.api.auth.interfaces;

import com.library.backend.api.auth.data.AuthenticationRequest;
import com.library.backend.api.auth.data.AuthenticationResponse;
import com.library.backend.api.auth.data.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<AuthenticationResponse> register(RegisterRequest request);

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
