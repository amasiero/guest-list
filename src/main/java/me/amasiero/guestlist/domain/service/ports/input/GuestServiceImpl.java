package me.amasiero.guestlist.domain.service.ports.input;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.amasiero.guestlist.domain.service.dto.Guest;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;
import me.amasiero.guestlist.domain.service.util.GuestHelper;

@Slf4j
@Service
@Validated
public record GuestServiceImpl(
    GuestRepository guestRepository,
    GuestHelper guestHelper
) implements GuestService {

    @Override
    public String createGuest(Guest guest) {
        guestHelper.validate(guest);
        return guestRepository.save(guest).name();
    }
}

