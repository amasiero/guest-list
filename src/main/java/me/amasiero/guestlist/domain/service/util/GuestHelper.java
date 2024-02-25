package me.amasiero.guestlist.domain.service.util;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.domain.service.dto.CreateGuest;

@Slf4j
@Component
public record GuestHelper(
    Validator validator
) {

    public void validate(CreateGuest createGuest) {
        var violations = validator.validate(createGuest);
        if (!violations.isEmpty()) {
            log.error("Guest validation failed: {}", violations);
            throw new ConstraintViolationException(violations);
        }
    }
}
