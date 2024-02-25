package me.amasiero.guestlist.domain.service.ports.input;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.amasiero.guestlist.domain.service.dto.CreateGuest;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;
import me.amasiero.guestlist.domain.service.util.ValidatorHelper;

@Slf4j
@Service
@Validated
public record GuestServiceImpl(
    GuestRepository guestRepository,
    ValidatorHelper validatorHelper
) implements GuestService {

    @Override
    public String createGuest(CreateGuest createGuest) {
        validatorHelper.validate(() -> createGuest);
        return guestRepository.save(createGuest).name();
    }
}

