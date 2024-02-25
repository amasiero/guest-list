package me.amasiero.guestlist.domain.service.mapper;

import lombok.experimental.UtilityClass;

import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.core.valueobject.Guest;
import me.amasiero.guestlist.domain.core.valueobject.Table;
import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateRequest;

@UtilityClass
public class ReservationDataMapper {
    public static Reservation fromGuestCreateRequest(ReservationCreateRequest reservationCreateRequest) {
        return Reservation.builder()
                          .guest(Guest.builder()
                                      .name(reservationCreateRequest.name())
                                      .accompanyingGuests(reservationCreateRequest.accompanyingGuests())
                                      .build())
                          .table(Table.builder()
                                      .id(reservationCreateRequest.table()
                                                                  .longValue())
                                      .build())
                          .build();
    }
}
