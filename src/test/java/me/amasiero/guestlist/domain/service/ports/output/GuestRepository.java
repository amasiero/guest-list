package me.amasiero.guestlist.domain.service.ports.output;

import me.amasiero.guestlist.domain.service.dto.Guest;

public interface GuestRepository {
    Guest save(Guest guest);
}
