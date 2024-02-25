package me.amasiero.guestlist.data.mapper;

import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.data.entity.TableEntity;
import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.core.valueobject.Guest;
import me.amasiero.guestlist.domain.core.valueobject.Table;

@Component
public record GuestDataAccessMapper() {
    public GuestEntity toEntity(Reservation reservation, Supplier<TableEntity> tableEntitySupplier) {
        return GuestEntity.builder()
                          .name(reservation.guest().name())
                          .timeArrived(reservation.timeArrived())
                          .table(tableEntitySupplier.get())
                          .accompanyingGuests(reservation.guest().accompanyingGuests())
                          .build();
    }

    public Reservation toDto(GuestEntity entity) {
        return Reservation.builder()
                          .guest(Guest.builder()
                                      .name(entity.getName())
                                      .accompanyingGuests(entity.getAccompanyingGuests())
                                      .build())
                          .table(Table.builder()
                                      .id(entity.getTable().getId())
                                      .capacity(entity.getTable().getSize())
                                      .build())
                          .timeArrived(entity.getTimeArrived())
                          .build();
    }
}
