package me.amasiero.guestlist.domain.service.dto.list;

import lombok.Builder;

@Builder
public record GuestDto(
    String name,
    Integer table,
    Integer accompanyingGuests
) {
    public GuestDtoBuilder toBuilder() {
        return new GuestDtoBuilder()
            .name(name)
            .table(table)
            .accompanyingGuests(accompanyingGuests);
    }
}
