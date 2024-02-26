package me.amasiero.guestlist.domain.service.dto.list;

import lombok.Builder;

@Builder
public record GuestArrivedDto(
    String name,
    Integer accompanyingGuests,
    String timeArrived
) {
    public GuestArrivedDtoBuilder toBuilder() {
        return new GuestArrivedDtoBuilder()
            .name(name)
            .accompanyingGuests(accompanyingGuests)
            .timeArrived(timeArrived);
    }
}
