package me.amasiero.guestlist.data.mapper;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.service.dto.Guest;

@Component
public record GuestDataAccessMapper() {
    public GuestEntity toEntity(Guest guest) {
        return GuestEntity.builder()
                          .name(guest.name())
                          .table(guest.table())
                          .accompanyingGuests(guest.accompanyingGuests())
                          .timeArrived(guest.timeArrived())
                          .build();
    }

    public Guest toDto(GuestEntity entity) {
        return Guest.builder()
                    .name(entity.getName())
                    .table(entity.getTable())
                    .accompanyingGuests(entity.getAccompanyingGuests())
                    .timeArrived(entity.getTimeArrived())
                    .build();
    }
}
