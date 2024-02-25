package me.amasiero.guestlist.data.adapter;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.mapper.GuestDataAccessMapper;
import me.amasiero.guestlist.data.repository.GuestJpaRepository;
import me.amasiero.guestlist.data.repository.TableJpaRepository;
import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.service.ports.output.ReservationRepository;

@Component
public record ReservationRepositoryImpl(
    TableJpaRepository tableRepository,
    GuestJpaRepository repository,
    GuestDataAccessMapper mapper
) implements ReservationRepository {
    @Override
    public Reservation save(Reservation reservation) {
        var entity = mapper.toEntity(reservation, () -> tableRepository
            .findById(reservation.table().id())
            .orElseThrow(() -> new IllegalArgumentException("Table not found")));
        var entitySaved = repository.save(entity);
        return mapper.toDto(entitySaved);
    }
}
