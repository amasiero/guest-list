package me.amasiero.guestlist.data.adapter;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.mapper.GuestDataAccessMapper;
import me.amasiero.guestlist.data.repository.GuestJpaRepository;
import me.amasiero.guestlist.domain.service.dto.Guest;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

@Component
public record GuestRepositoryImpl(
    GuestJpaRepository repository,
    GuestDataAccessMapper mapper
) implements GuestRepository {
    @Override
    public Guest save(Guest guest) {
        var entity = mapper.toEntity(guest);
        return mapper.toDto(repository.save(entity));
    }
}
