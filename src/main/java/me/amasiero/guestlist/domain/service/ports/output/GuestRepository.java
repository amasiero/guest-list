package me.amasiero.guestlist.domain.service.ports.output;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.core.entity.Reservation;

public interface GuestRepository {
    Reservation save(GuestEntity guestEntity);

    GuestEntity getGuestEntity(Reservation reservation);

    @Query("select count(t) > 0 from TableEntity t where t.status = 0")
    Boolean hasTableAvailable();

    List<GuestEntity> findAll();
}
