package me.amasiero.guestlist.domain.service.ports.input;

import jakarta.validation.Valid;

import me.amasiero.guestlist.domain.service.dto.Guest;

public interface GuestService {
    Guest createGuest(@Valid Guest guest);
}
