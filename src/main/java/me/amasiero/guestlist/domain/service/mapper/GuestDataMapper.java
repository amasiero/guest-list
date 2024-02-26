package me.amasiero.guestlist.domain.service.mapper;

import lombok.experimental.UtilityClass;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.service.dto.list.GuestDto;

@UtilityClass
public class GuestDataMapper {

    public static GuestDto fromEntity(GuestEntity guestEntity) {
        return GuestDto.builder()
                       .name(guestEntity.getName())
                       .table(guestEntity.getTable().getId().intValue())
                       .accompanyingGuests(guestEntity.getAccompanyingGuests())
                       .build();
    }
}
