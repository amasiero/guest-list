package me.amasiero.guestlist.domain.service.util;

import java.util.function.Supplier;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public record ValidatorHelper(
    Validator validator
) {

    public <T> void validate(Supplier<T> createGuest) {
        var violations = validator.validate(createGuest.get());
        if (!violations.isEmpty()) {
            log.error("Guest validation failed: {}", violations);
            throw new ConstraintViolationException(violations);
        }
    }
}
