package com.library.backend.utils.exceptions.data;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(String message, String exceptionName, LocalDateTime timeStamp) {
}
