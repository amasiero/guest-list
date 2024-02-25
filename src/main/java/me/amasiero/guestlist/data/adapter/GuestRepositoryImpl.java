package me.amasiero.guestlist.data.adapter;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.data.mapper.GuestDataAccessMapper;
import me.amasiero.guestlist.data.repository.GuestJpaRepository;
import me.amasiero.guestlist.data.repository.TableJpaRepository;
import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.core.exception.TableNotFoundException;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

@Component
public record GuestRepositoryImpl(
    TableJpaRepository tableRepository,
    GuestJpaRepository repository,
    GuestDataAccessMapper mapper
) implements GuestRepository {
    @Override
    public Reservation save(GuestEntity guestEntity) {
        var entitySaved = repository.save(guestEntity);
        return mapper.toDto(entitySaved);
    }

    @Override
    public GuestEntity getGuestEntity(Reservation reservation) {
        return mapper.toEntity(reservation, () -> tableRepository
            .findById(reservation.table().id())
            .orElseThrow(() -> new TableNotFoundException(
                "Table with number %s not found."
                    .formatted(reservation.table().id())
            )));
    }
}
