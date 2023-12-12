package com.library.backend.config.jwt.interfaces;

import com.library.backend.utils.exceptions.users.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUsername(String token);

    String generateToken(UserDetails userDetails);


    boolean isTokenValid(String token, UserDetails userDetails);

}
