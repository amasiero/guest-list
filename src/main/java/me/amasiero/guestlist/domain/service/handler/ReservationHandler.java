package me.amasiero.guestlist.domain.service.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.core.exception.TableNotAvailableException;
import me.amasiero.guestlist.domain.core.exception.TableOutOfCapacityException;
import me.amasiero.guestlist.domain.core.valueobject.Guest;
import me.amasiero.guestlist.domain.core.valueobject.TableStatus;
import me.amasiero.guestlist.domain.service.dto.list.GuestArrivedDto;
import me.amasiero.guestlist.domain.service.dto.list.GuestDto;
import me.amasiero.guestlist.domain.service.mapper.GuestDataMapper;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

@Component
public record ReservationHandler(
    GuestRepository guestRepository
) {

    public <T> Reservation createReservation(T data, Function<T, Reservation> converter) {
        var reservation = converter.apply(data);
        var guest = guestRepository.getGuestEntity(reservation);
        validateGuest(guest);
        guest.getTable().setStatus(TableStatus.RESERVED);
        return guestRepository.save(guest);
    }

    public List<GuestDto> listGuests() {
        var guests = guestRepository.findAll();
        return guests.stream()
                     .map(GuestDataMapper::fromEntity)
                     .toList();
    }

    public <T> void updateReservation(T guest, Function<T, Reservation> converter) {
        var reservation = converter.apply(guest);
        var guestEntity = guestRepository.getGuestEntity(reservation);
        validateArrival(guestEntity, reservation);
        guestEntity.getTable().setStatus(TableStatus.OCCUPIED);
        guestEntity.setTimeArrived(LocalDateTime.now().toString());
        guestRepository.save(guestEntity);
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

    private void validateArrival(GuestEntity guestEntity, Reservation reservation) {
        if (reservation.guest().accompanyingGuests() > guestEntity.getTable().getSize()) {
            throw new TableOutOfCapacityException("The table is too small for the number of guests");
        }
    }

    public List<GuestArrivedDto> listOfArrivals() {
        var guests = guestRepository.findAll();
        return guests.stream()
                     .filter(guest -> guest.getTimeArrived() != null)
                     .map(GuestDataMapper::fromEntityArrived)
                     .toList();
    }

    public void guestLeave(String name) {
        var guest = guestRepository.getGuestEntity(Reservation.builder()
                                                              .guest(Guest.builder()
                                                                          .name(name)
                                                                          .build())
                                                              .build());
        guest.getTable().setStatus(TableStatus.AVAILABLE);
        guestRepository.delete(guest);
    }
}
