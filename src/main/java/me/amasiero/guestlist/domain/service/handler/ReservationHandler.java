package me.amasiero.guestlist.domain.service.handler;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.core.exception.TableOutOfCapacityException;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;
import me.amasiero.guestlist.domain.service.util.ValidatorHelper;

@Component
public record ReservationHandler(
    GuestRepository guestRepository,
    ValidatorHelper validatorHelper
) {

    public <T> Reservation createReservation(T data, Function<T, Reservation> converter) {
        var reservation = converter.apply(data);
        validatorHelper.validate(() -> reservation);
        var guest = guestRepository.getGuestEntity(reservation);
        validateGuest(guest);
        return guestRepository.save(guest);
    }

    private void validateGuest(GuestEntity guest) {
        if (guest.getAccompanyingGuests() > guest.getTable().getSize()) {
            throw new TableOutOfCapacityException("The table is too small for the number of guests");
        }
    }
}
