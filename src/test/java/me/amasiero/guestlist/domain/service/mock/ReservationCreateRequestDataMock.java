package me.amasiero.guestlist.domain.service.mock;

import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateRequest;

public class ReservationCreateRequestDataMock {
    protected ReservationCreateRequestDataMock() {
    }

    public static ReservationCreateRequest.ReservationCreateRequestBuilder anGuestCreateRequest() {
        return ReservationCreateRequest.builder()
                                       .name("John Doe")
                                       .accompanyingGuests(2)
                                       .table(1);
    }

    public static ReservationCreateRequest build() {
        return anGuestCreateRequest().build();
    }

    public static ReservationCreateRequest buildWithName(String name) {
        return anGuestCreateRequest().name(name).build();
    }

    public static ReservationCreateRequest buildWithAccompanyingGuests(Integer accompanyingGuests) {
        return anGuestCreateRequest().accompanyingGuests(accompanyingGuests).build();
    }

    public static ReservationCreateRequest buildWithTable(Integer table) {
        return anGuestCreateRequest().table(table).build();
    }
}
