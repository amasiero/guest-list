package me.amasiero.guestlist.domain.service.dto.mock;

import java.util.List;

import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.core.valueobject.Guest;
import me.amasiero.guestlist.domain.core.valueobject.Table;

public class ReservationDataMock {

    protected ReservationDataMock() {
    }

    public static Reservation.ReservationBuilder anReservation() {
        return Reservation.builder()
                          .guest(GuestDataMock.build())
                          .table(TableDataMock.build())
                          .timeArrived("2024-02-25T12:00:00");
    }

    public static Reservation build() {
        return anReservation().build();
    }

    public static Reservation buildWithGuest(Guest guest) {
        return anReservation().guest(guest).build();
    }

    public static Reservation buildWithTable(Table table) {
        return anReservation().table(table).build();
    }

    public static Reservation buildWithTimeArrived(String timeArrived) {
        return anReservation().timeArrived(timeArrived).build();
    }

    public static List<Reservation> buildList() {
        return List.of(build());
    }
}
