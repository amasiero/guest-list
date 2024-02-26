package me.amasiero.guestlist.domain.service.ports.input;

import jakarta.validation.constraints.NotNull;

import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateRequest;
import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateResponse;
import me.amasiero.guestlist.domain.service.dto.list.GuestListResponse;
import me.amasiero.guestlist.domain.service.dto.update.ReservationUpdateRequest;
import me.amasiero.guestlist.domain.service.dto.update.ReservationUpdateResponse;

public interface ReservationService {
    ReservationCreateResponse createReservation(@NotNull ReservationCreateRequest createGuest);

    GuestListResponse listGuests();

    ReservationUpdateResponse updateReservation(ReservationUpdateRequest build);
}
