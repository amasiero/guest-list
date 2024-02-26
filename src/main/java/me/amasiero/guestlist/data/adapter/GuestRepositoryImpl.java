package me.amasiero.guestlist.data.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.data.entity.TableEntity;
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
        var currentEntity = getEntity(reservation);
        var guestEntity = repository.findByName(reservation.guest().name());

        if (guestEntity.isEmpty()) {
            return currentEntity;
        }

        guestEntity.ifPresent(entity -> {
            entity.setAccompanyingGuests(currentEntity.getAccompanyingGuests());
            entity.setTimeArrived(currentEntity.getTimeArrived());
            entity.setTable(currentEntity.getTable());
        });
        return guestEntity.get();
    }

    private GuestEntity getEntity(Reservation reservation) {
        return mapper.toEntity(reservation, () -> reservation.table() != null ?
            tableRepository.findById(reservation.table().id())
                           .orElseThrow(() -> new TableNotFoundException(
                               "Table with number %s not found."
                                   .formatted(reservation.table().id())
                           )) :
            tableRepository.findByGuestName(reservation.guest().name())
                           .orElseThrow(() -> new TableNotFoundException(
                               "Table with guest name %s not found."
                                   .formatted(reservation.guest().name())
                           )));
    }

    @Override
    public Boolean hasTableAvailable() {
        return tableRepository.hasTableAvailable();
    }

    @Override
    public List<GuestEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(GuestEntity guest) {
        var table = guest.getTable();
        tableRepository.save(table);
        repository.delete(guest);
    }

    @Override
    public List<TableEntity> findAllTables() {
        return tableRepository.findAll();
    }
}
