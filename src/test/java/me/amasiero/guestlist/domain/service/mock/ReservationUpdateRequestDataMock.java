package me.amasiero.guestlist.domain.service.mock;

import me.amasiero.guestlist.domain.service.dto.update.ReservationUpdateRequest;

public class ReservationUpdateRequestDataMock {

    protected ReservationUpdateRequestDataMock() {
    }

    public static ReservationUpdateRequest.ReservationUpdateRequestBuilder anGuestUpdateRequest() {
        return ReservationUpdateRequest.builder()
                                       .name("John Doe")
                                       .accompanyingGuests(2);
    }

    public static ReservationUpdateRequest build() {
        return anGuestUpdateRequest().build();
    }

    public static ReservationUpdateRequest buildWithName(String name) {
        return anGuestUpdateRequest().name(name).build();
    }

    public static ReservationUpdateRequest buildWithAccompanyingGuests(Integer accompanyingGuests) {
        return anGuestUpdateRequest().accompanyingGuests(accompanyingGuests).build();
    }

}
