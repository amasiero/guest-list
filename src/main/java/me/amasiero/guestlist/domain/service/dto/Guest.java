package me.amasiero.guestlist.domain.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record Guest(
    @NotBlank(message = "Name is mandatory")
    String name,
    @NotNull(message = "Table number is mandatory")
    @Min(value = 1, message = "Table number must be greater than 0")
    Integer table,
    @NotNull(message = "Accompanying guests is mandatory")
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
