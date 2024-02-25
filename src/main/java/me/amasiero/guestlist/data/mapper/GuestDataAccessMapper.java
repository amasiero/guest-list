package me.amasiero.guestlist.data.mapper;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.service.dto.CreateGuest;

@Component
public record GuestDataAccessMapper() {
    public GuestEntity toEntity(CreateGuest createGuest) {
        return GuestEntity.builder()
                          .name(createGuest.name())
//                          .table(guest.table())
                          .accompanyingGuests(createGuest.accompanyingGuests())
                          .timeArrived(createGuest.timeArrived())
                          .build();
    }

    public CreateGuest toDto(GuestEntity entity) {
        return CreateGuest.builder()
                          .name(entity.getName())
//                    .table(entity.getTable())
                          .accompanyingGuests(entity.getAccompanyingGuests())
                          .timeArrived(entity.getTimeArrived())
                          .build();
    }
}
