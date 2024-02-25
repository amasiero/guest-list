package me.amasiero.guestlist.domain.core.valueobject;

import lombok.Builder;

@Builder
public record Table(Long id, Integer capacity) {
    public TableBuilder toBuilder() {
        return new TableBuilder().id(id).capacity(capacity);
    }
}
