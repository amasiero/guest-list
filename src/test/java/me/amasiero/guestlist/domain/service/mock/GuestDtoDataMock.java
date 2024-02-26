package me.amasiero.guestlist.domain.service.mock;

import me.amasiero.guestlist.domain.service.dto.list.GuestDto;

public class GuestDtoDataMock {

    protected GuestDtoDataMock() {
    }

    public static GuestDto.GuestDtoBuilder anGuestDto() {
        return GuestDto.builder()
                       .name("John Doe")
                       .table(1)
                       .accompanyingGuests(2);
    }

    public static GuestDto build() {
        return anGuestDto().build();
    }

    public static GuestDto buildWithName(String name) {
        return anGuestDto().name(name).build();
    }

    public static GuestDto buildWithTable(Integer table) {
        return anGuestDto().table(table).build();
    }

    public static GuestDto buildWithAccompanyingGuests(Integer accompanyingGuests) {
        return anGuestDto().accompanyingGuests(accompanyingGuests).build();
    }
}
