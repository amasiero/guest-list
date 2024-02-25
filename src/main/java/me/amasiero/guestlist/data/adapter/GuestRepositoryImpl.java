package me.amasiero.guestlist.data.adapter;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.mapper.GuestDataAccessMapper;
import me.amasiero.guestlist.data.repository.GuestJpaRepository;
import me.amasiero.guestlist.domain.service.dto.CreateGuest;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

@Component
public record GuestRepositoryImpl(
    GuestJpaRepository repository,
    GuestDataAccessMapper mapper
) implements GuestRepository {
    @Override
    public CreateGuest save(CreateGuest createGuest) {
        var entity = mapper.toEntity(createGuest);
        return mapper.toDto(repository.save(entity));
    }
}
