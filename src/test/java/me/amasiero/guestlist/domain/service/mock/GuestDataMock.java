package me.amasiero.guestlist.domain.service.mock;

import me.amasiero.guestlist.domain.core.valueobject.Guest;

public class GuestDataMock {
    protected GuestDataMock() {
    }

    public static Guest.GuestBuilder anGuest() {
        return Guest.builder()
                    .name("John Doe")
                    .accompanyingGuests(2);
    }

    public static Guest build() {
        return anGuest().build();
    }

    public static Guest buildWithName(String name) {
        return anGuest().name(name).build();
    }

    public static Guest buildWithAccompanyingGuests(int accompanyingGuests) {
        return anGuest().accompanyingGuests(accompanyingGuests).build();
    }
}
