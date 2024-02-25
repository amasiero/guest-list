package me.amasiero.guestlist.domain.service.dto.mock;

import java.util.List;

import me.amasiero.guestlist.domain.service.dto.CreateGuest;

public class GuestDataMock {

    protected GuestDataMock() {
    }

    public static CreateGuest.CreateGuestBuilder anGuest() {
        return CreateGuest.builder()
                          .name("John Doe")
                          .table(1)
                          .accompanyingGuests(2);
    }

    public static CreateGuest build() {
        return anGuest().build();
    }

    public static CreateGuest buildWithName(String name) {
        return anGuest().name(name).build();
    }

    public static CreateGuest buildWithTable(Integer table) {
        return anGuest().table(table).build();
    }

    public static CreateGuest buildWithAccompanyingGuests(Integer accompanyingGuests) {
        return anGuest().accompanyingGuests(accompanyingGuests).build();
    }

    public static List<CreateGuest> buildList() {
        return List.of(build());
    }
}
