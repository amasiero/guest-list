package me.amasiero.guestlist.domain.service;


import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.amasiero.guestlist.domain.service.dto.Guest;
import me.amasiero.guestlist.domain.service.ports.input.GuestService;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

@Slf4j
@Service
@Validated
public record GuestServiceImpl(
    GuestRepository guestRepository,
    Validator validator
) implements GuestService {

    @Override
    public Guest createGuest(Guest guest) {
        validateGuest(guest);
        return guestRepository.save(guest);
    }

    private void validateGuest(Guest guest) {
        var violations = validator.validate(guest);
        if (!violations.isEmpty()) {
            log.error("Guest validation failed: {}", violations);
            throw new ConstraintViolationException(violations);
        }
    }
}

