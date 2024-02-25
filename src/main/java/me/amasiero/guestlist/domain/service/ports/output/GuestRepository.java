package me.amasiero.guestlist.domain.service.ports.output;

import me.amasiero.guestlist.domain.service.dto.CreateGuest;

public interface GuestRepository {
    CreateGuest save(CreateGuest createGuest);
}
