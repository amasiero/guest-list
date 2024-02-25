package me.amasiero.guestlist.domain.service.ports.input;

import jakarta.validation.constraints.NotNull;

import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateRequest;
import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateResponse;

public interface ReservationService {
    ReservationCreateResponse createReservation(@NotNull ReservationCreateRequest createGuest);
}
