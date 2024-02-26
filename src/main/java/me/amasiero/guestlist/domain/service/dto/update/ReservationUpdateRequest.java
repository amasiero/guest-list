package me.amasiero.guestlist.domain.service.dto.update;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReservationUpdateRequest(
    @JsonProperty("accompanying_guests")
    Integer accompanyingGuests
) {
}
