package me.amasiero.guestlist.domain.service.ports.input;

import me.amasiero.guestlist.domain.service.dto.Guest;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

public record GuestServiceImpl(
    GuestRepository guestRepository
) implements GuestService {

    @Override
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }
}
