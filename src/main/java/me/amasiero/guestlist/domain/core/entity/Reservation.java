package me.amasiero.guestlist.domain.core.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import me.amasiero.guestlist.domain.core.valueobject.Guest;
import me.amasiero.guestlist.domain.core.valueobject.Table;

@Builder
public record Reservation(
    @NotNull
    Table table,
    @NotNull
    Guest guest,
    String timeArrived
) {
    public ReservationBuilder toBuilder() {
        return new ReservationBuilder()
            .table(table)
            .guest(guest)
            .timeArrived(timeArrived);
    }
}
