package me.amasiero.guestlist.domain.service.handler;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.entity.TableEntity;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

@Component
public record EmptySeatsHandler(
    GuestRepository guestRepository
) {
    public int emptySeats() {
        var tables = guestRepository.findAllTables();
        return tables.stream()
                     .reduce(0, (acc, table) ->
                             acc + getSeats(table),
                         Integer::sum);
    }

    private static int getSeats(TableEntity table) {
        return table.getGuest() == null ? table.getSize() : table.getSize() - table.getGuest().getAccompanyingGuests();
    }
}
