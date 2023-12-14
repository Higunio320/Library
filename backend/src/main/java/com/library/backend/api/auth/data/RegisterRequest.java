package com.library.backend.api.auth.data;

import lombok.Builder;
import lombok.ToString;

@Builder
public record RegisterRequest(String firstName, String lastName, String email, String password) {
}
