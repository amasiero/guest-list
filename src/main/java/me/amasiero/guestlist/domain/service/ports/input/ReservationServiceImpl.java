package me.amasiero.guestlist.domain.service.ports.input;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateRequest;
import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateResponse;
import me.amasiero.guestlist.domain.service.dto.list.GuestArrivedDto;
import me.amasiero.guestlist.domain.service.dto.list.GuestDto;
import me.amasiero.guestlist.domain.service.dto.list.GuestListResponse;
import me.amasiero.guestlist.domain.service.dto.seat.EmptySeatsResponse;
import me.amasiero.guestlist.domain.service.dto.update.ReservationUpdateRequest;
import me.amasiero.guestlist.domain.service.dto.update.ReservationUpdateResponse;
import me.amasiero.guestlist.domain.service.handler.EmptySeatsHandler;
import me.amasiero.guestlist.domain.service.handler.ReservationHandler;
import me.amasiero.guestlist.domain.service.mapper.ReservationDataMapper;
import me.amasiero.guestlist.domain.service.util.ValidatorHelper;

@Slf4j
@Service
@Validated
public record ReservationServiceImpl(
    ReservationHandler reservationHandler,

    EmptySeatsHandler emptySeatsHandler,
    ValidatorHelper validatorHelper
) implements ReservationService {

    @Override
    public ReservationCreateResponse createReservation(ReservationCreateRequest guest) {
        validatorHelper.validate(() -> guest);
        var reservationCreated = reservationHandler.createReservation(
            guest,
            ReservationDataMapper::fromRequest
        );
        return new ReservationCreateResponse(reservationCreated.guest().name());
    }

    @Override
    public GuestListResponse<GuestDto> listGuests() {
        var list = reservationHandler.listGuests();
        return new GuestListResponse<>(list);
    }

    @Override
    public ReservationUpdateResponse updateReservation(ReservationUpdateRequest guest) {
        validatorHelper.validate(() -> guest);
        reservationHandler.updateReservation(
            guest,
            ReservationDataMapper::fromRequest
        );
        return new ReservationUpdateResponse(guest.name());
    }

    @Override
    public GuestListResponse<GuestArrivedDto> listOfArrivals() {
        var list = reservationHandler.listOfArrivals();
        return new GuestListResponse<>(list);
    }

    @Override
    public void guestLeave(String name) {
        reservationHandler.guestLeave(name);
    }

    @Override
    public EmptySeatsResponse retrieveEmptySeats() {
        return new EmptySeatsResponse(emptySeatsHandler.emptySeats());
    }
}

