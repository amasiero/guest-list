package me.amasiero.guestlist.application.exception;

import lombok.Builder;

@Builder
public record ErrorDto(
    String code,
    String message
) {
}
