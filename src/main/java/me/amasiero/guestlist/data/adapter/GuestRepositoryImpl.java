package me.amasiero.guestlist.data.adapter;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.domain.service.dto.Guest;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

@Component
public record GuestRepositoryImpl() implements GuestRepository {
    @Override
    public Guest save(Guest guest) {
        return null;
    }
}
