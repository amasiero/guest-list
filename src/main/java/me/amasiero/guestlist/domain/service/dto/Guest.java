package me.amasiero.guestlist.domain.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record Guest(
    @NotBlank(message = "Name is mandatory")
    String name,
    @Min(value = 1, message = "Table number must be greater than 0")
    Integer table,
    @Min(value = 1, message = "Accompanying guests must be greater than 0")
    Integer accompanyingGuests,
    String timeArrived
) {
    public GuestBuilder toBuilder() {
        return new GuestBuilder()
            .name(name)
            .table(table)
            .accompanyingGuests(accompanyingGuests)
            .timeArrived(timeArrived);
    }
}
