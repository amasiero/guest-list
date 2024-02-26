package me.amasiero.guestlist.domain.service.mapper;

import lombok.experimental.UtilityClass;

import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.core.valueobject.Guest;
import me.amasiero.guestlist.domain.core.valueobject.Table;
import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateRequest;
import me.amasiero.guestlist.domain.service.dto.update.ReservationUpdateRequest;

@UtilityClass
public class ReservationDataMapper {
    public static <T> Reservation fromRequest(T data) {
        return switch (data) {
            case ReservationCreateRequest request -> fromReservationCreateRequest(request);
            case ReservationUpdateRequest request -> fromReservationUpdateRequest(request);
            default -> throw new IllegalArgumentException("Invalid request type");
        };
    }

    private static Reservation fromReservationUpdateRequest(ReservationUpdateRequest request) {
        return Reservation.builder()
                          .guest(Guest.builder()
                                      .name(request.name())
                                      .accompanyingGuests(request.accompanyingGuests())
                                      .build())
                          .build();
    }

    private static Reservation fromReservationCreateRequest(ReservationCreateRequest request) {
        return Reservation.builder()
                          .guest(Guest.builder()
                                      .name(request.name())
                                      .accompanyingGuests(request.accompanyingGuests())
                                      .build())
                          .table(Table.builder()
                                      .id(request.table()
                                                 .longValue())
                                      .build())
                          .build();
    }
}
