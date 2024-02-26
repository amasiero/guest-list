package me.amasiero.guestlist.domain.service.mock;

import me.amasiero.guestlist.data.entity.TableEntity;
import me.amasiero.guestlist.domain.core.valueobject.TableStatus;

public class TableEntityDataMock {

    protected TableEntityDataMock() {
    }

    public static TableEntity.TableEntityBuilder anTableEntity() {
        return TableEntity.builder()
                          .id(1L)
                          .size(4)
                          .status(TableStatus.AVAILABLE);
    }

    public static TableEntity build() {
        return anTableEntity().build();
    }

    public static TableEntity anTableEntityWithId(Long id) {
        return anTableEntity()
            .id(id)
            .build();
    }

    public static TableEntity anTableEntityWithSize(Integer size) {
        return anTableEntity()
            .size(size)
            .build();
    }

    public static TableEntity anTableEntityWithStatus(TableStatus status) {
        return anTableEntity()
            .status(status)
            .build();
    }
}
