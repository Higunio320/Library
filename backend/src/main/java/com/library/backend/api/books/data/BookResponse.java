package com.library.backend.api.books.data;

import lombok.Builder;

@Builder
public record BookResponse(long id, String title, String author, String genre, String description, int availableNumber) {
}
