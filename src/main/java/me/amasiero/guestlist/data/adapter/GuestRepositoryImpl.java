package me.amasiero.guestlist.data.adapter;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.mapper.GuestDataAccessMapper;
import me.amasiero.guestlist.data.repository.EventJpaRepository;
import me.amasiero.guestlist.data.repository.GuestJpaRepository;
import me.amasiero.guestlist.domain.service.dto.Guest;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

@Component
public record GuestRepositoryImpl(
    GuestJpaRepository repository,
    GuestDataAccessMapper mapper,
    EventJpaRepository eventRepository
) implements GuestRepository {
    @Override
    public Guest save(Guest guest) {
        var event = eventRepository.findAll()
                                   .stream()
                                   .findFirst()
                                   .orElseThrow(() -> new RuntimeException("No event found"));
        var entity = mapper.toEntity(guest);
        entity.setEvent(event);
        return mapper.toDto(repository.save(entity));
    }
}
