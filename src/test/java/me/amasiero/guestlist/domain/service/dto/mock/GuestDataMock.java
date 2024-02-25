package me.amasiero.guestlist.domain.service.dto.mock;

import java.util.List;

import me.amasiero.guestlist.domain.service.dto.Guest;

public class GuestDataMock {

    protected GuestDataMock() {
    }

    public static Guest.GuestBuilder anGuest() {
        return Guest.builder()
                    .name("John Doe")
                    .table(1)
                    .accompanyingGuests(2);
    }

    public static Guest build() {
        return anGuest().build();
    }

    public static Guest buildWithName(String name) {
        return anGuest().name(name).build();
    }

    public static Guest buildWithTable(Integer table) {
        return anGuest().table(table).build();
    }

    public static Guest buildWithAccompanyingGuests(Integer accompanyingGuests) {
        return anGuest().accompanyingGuests(accompanyingGuests).build();
    }

    public static List<Guest> buildList() {
        return List.of(build());
    }
}
