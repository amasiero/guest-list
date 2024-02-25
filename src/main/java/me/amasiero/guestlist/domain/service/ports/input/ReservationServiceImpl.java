package me.amasiero.guestlist.domain.service.ports.input;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateRequest;
import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateResponse;
import me.amasiero.guestlist.domain.service.handler.ReservationHandler;
import me.amasiero.guestlist.domain.service.mapper.ReservationDataMapper;
import me.amasiero.guestlist.domain.service.util.ValidatorHelper;

@Slf4j
@Service
@Validated
public record ReservationServiceImpl(
    ReservationHandler reservationHandler,
    ValidatorHelper validatorHelper
) implements ReservationService {

    @Override
    public ReservationCreateResponse createReservation(ReservationCreateRequest guest) {
        validatorHelper.validate(() -> guest);
        var reservationCreated = reservationHandler.createReservation(
            guest,
            ReservationDataMapper::fromGuestCreateRequest
        );
        return new ReservationCreateResponse(reservationCreated.guest().name());
    }
}

