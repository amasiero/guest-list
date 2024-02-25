package me.amasiero.guestlist.domain.service.ports.input;

import jakarta.validation.constraints.NotNull;

import me.amasiero.guestlist.domain.service.dto.Guest;

public interface GuestService {
    String createGuest(@NotNull Guest guest);
}
