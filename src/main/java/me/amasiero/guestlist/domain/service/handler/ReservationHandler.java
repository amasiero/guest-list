package me.amasiero.guestlist.domain.service.handler;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.core.exception.TableNotAvailableException;
import me.amasiero.guestlist.domain.core.exception.TableOutOfCapacityException;
import me.amasiero.guestlist.domain.core.valueobject.TableStatus;
import me.amasiero.guestlist.domain.service.dto.list.GuestDto;
import me.amasiero.guestlist.domain.service.mapper.GuestDataMapper;
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

        if (guest.getTable().getStatus() != TableStatus.AVAILABLE) {
            throw new TableNotAvailableException("The table is not available");
        }

        if (!guestRepository.hasTableAvailable()) {
            throw new TableOutOfCapacityException("There are no tables available");
        }
    }

    public List<GuestDto> listGuests() {
        var guests = guestRepository.findAll();
        return guests.stream()
                     .map(GuestDataMapper::fromEntity)
                     .toList();
    }
}
