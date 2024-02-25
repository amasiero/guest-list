package me.amasiero.guestlist.domain.service.dto.mock;

import me.amasiero.guestlist.domain.core.valueobject.Table;

public class TableDataMock {

    protected TableDataMock() {
    }

    public static Table.TableBuilder anTable() {
        return Table.builder()
                    .id(1L)
                    .capacity(4);
    }

    public static Table build() {
        return anTable().build();
    }

    public static Table anTableWithId(Long id) {
        return anTable()
            .id(id)
            .build();
    }

    public static Table anTableWithCapacity(Integer capacity) {
        return anTable()
            .capacity(capacity)
            .build();
    }
}
