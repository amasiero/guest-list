package me.amasiero.guestlist.domain.core.valueobject;

import lombok.Builder;

@Builder
public record Table(Long id, Integer capacity, TableStatus status) {
    public TableBuilder toBuilder() {
        return Table.builder()
                    .id(id)
                    .capacity(capacity)
                    .status(status);
    }
}
