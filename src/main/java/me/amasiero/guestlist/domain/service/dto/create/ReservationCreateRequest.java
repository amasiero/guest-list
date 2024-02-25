package me.amasiero.guestlist.domain.service.dto.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ReservationCreateRequest(
    @NotBlank(message = "Name is required")
    String name,
    @NotNull(message = "Table is required")
    @Min(value = 1, message = "Table must be greater than 0")
    Integer table,
    @NotNull(message = "Accompanying guests is required")
    @Min(value = 0, message = "Accompanying guests must be greater than or equal to 0")
    Integer accompanyingGuests
) {
    public ReservationCreateRequestBuilder toBuilder() {
        return ReservationCreateRequest.builder()
                                       .name(name)
                                       .table(table)
                                       .accompanyingGuests(accompanyingGuests);
    }
}
