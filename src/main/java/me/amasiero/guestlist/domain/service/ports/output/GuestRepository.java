package me.amasiero.guestlist.domain.service.ports.output;

import me.amasiero.guestlist.domain.core.entity.Reservation;

public interface ReservationRepository {
    Reservation save(Reservation guest);
}
