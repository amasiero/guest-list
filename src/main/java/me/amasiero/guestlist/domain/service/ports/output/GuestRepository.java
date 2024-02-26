package me.amasiero.guestlist.domain.service.ports.output;

import java.util.List;

import jakarta.transaction.Transactional;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.core.entity.Reservation;

public interface GuestRepository {
    @Transactional
    Reservation save(GuestEntity guestEntity);

    GuestEntity getGuestEntity(Reservation reservation);

    Boolean hasTableAvailable();

    List<GuestEntity> findAll();

    @Transactional
    void delete(GuestEntity guest);
}
