package com.library.backend.api.auth.interfaces;

import com.library.backend.api.auth.data.AuthenticationRequest;
import com.library.backend.api.auth.data.AuthenticationResponse;
import com.library.backend.api.auth.data.RegisterRequest;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
