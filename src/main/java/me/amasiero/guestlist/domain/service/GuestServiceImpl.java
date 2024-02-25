package me.amasiero.guestlist.domain.service;


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
    GuestRepository guestRepository
) implements GuestService {

    @Override
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }
}

