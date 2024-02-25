package me.amasiero.guestlist.domain.service.ports.output;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.core.entity.Reservation;

public interface GuestRepository {
    Reservation save(GuestEntity guestEntity);

    GuestEntity getGuestEntity(Reservation reservation);

    Boolean hasTableAvailable();
}
