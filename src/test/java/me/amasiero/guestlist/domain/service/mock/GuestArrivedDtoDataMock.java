package me.amasiero.guestlist.domain.service.mock;

import me.amasiero.guestlist.domain.service.dto.list.GuestArrivedDto;

public class GuestArrivedDtoDataMock {
    protected GuestArrivedDtoDataMock() {
    }

    public static GuestArrivedDto.GuestArrivedDtoBuilder anGuestArrivedDto() {
        return GuestArrivedDto.builder()
                              .name("John Doe")
                              .accompanyingGuests(2)
                              .timeArrived("2021-01-01T00:00:00");
    }

    public static GuestArrivedDto build() {
        return anGuestArrivedDto().build();
    }

    public static GuestArrivedDto buildWithName(String name) {
        return anGuestArrivedDto().name(name).build();
    }

    public static GuestArrivedDto buildWithAccompanyingGuests(Integer accompanyingGuests) {
        return anGuestArrivedDto().accompanyingGuests(accompanyingGuests).build();
    }

    public static GuestArrivedDto buildWithTimeArrived(String timeArrived) {
        return anGuestArrivedDto().timeArrived(timeArrived).build();
    }
}
