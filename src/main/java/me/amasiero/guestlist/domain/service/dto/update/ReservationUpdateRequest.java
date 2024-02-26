package me.amasiero.guestlist.domain.service.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ReservationUpdateRequest(
    @NotBlank(message = "Name is required")
    String name,
    @NotNull(message = "Accompanying guests is required")
    @Min(value = 0, message = "Accompanying guests must be greater than or equal to 0")
    Integer accompanyingGuests
) {
    public ReservationUpdateRequestBuilder toBuilder() {
        return ReservationUpdateRequest.builder()
                                       .name(name)
                                       .accompanyingGuests(accompanyingGuests);
    }
}
