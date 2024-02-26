package me.amasiero.guestlist.domain.service.mock;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.data.entity.TableEntity;

public class GuestEntityDataMock {

    protected GuestEntityDataMock() {
    }

    public static GuestEntity.GuestEntityBuilder anGuestEntity() {
        return GuestEntity.builder()
                          .id(1L)
                          .name("John Doe")
                          .accompanyingGuests(2)
                          .timeArrived("")
                          .table(TableEntityDataMock.build());
    }

    public static GuestEntity build() {
        return anGuestEntity().build();
    }

    public static GuestEntity anGuestEntityWithId(Long id) {
        return anGuestEntity()
            .id(id)
            .build();
    }

    public static GuestEntity anGuestEntityWithName(String name) {
        return anGuestEntity()
            .name(name)
            .build();
    }

    public static GuestEntity anGuestEntityWithAccompanyingGuests(int accompanyingGuests) {
        return anGuestEntity()
            .accompanyingGuests(accompanyingGuests)
            .build();
    }

    public static GuestEntity anGuestEntityWithTimeArrived(String timeArrived) {
        return anGuestEntity()
            .timeArrived(timeArrived)
            .build();
    }

    public static GuestEntity anGuestEntityWithTable(TableEntity table) {
        return anGuestEntity()
            .table(table)
            .build();
    }
}
