package me.amasiero.guestlist.domain.core.valueobject;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record Guest(
    @NotBlank(message = "Name is mandatory")
    String name,
    @NotNull(message = "Accompanying guests is mandatory")
    @Min(value = 1, message = "Accompanying guests must be greater than 0")
    Integer accompanyingGuests
) {
    public GuestBuilder toBuilder() {
        return new GuestBuilder()
            .name(name)
            .accompanyingGuests(accompanyingGuests);
    }
}
