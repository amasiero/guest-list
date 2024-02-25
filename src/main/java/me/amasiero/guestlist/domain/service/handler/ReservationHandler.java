package me.amasiero.guestlist.domain.service.handler;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.service.ports.output.ReservationRepository;
import me.amasiero.guestlist.domain.service.util.ValidatorHelper;

@Component
public record ReservationHandler(
    ReservationRepository reservationRepository,
    ValidatorHelper validatorHelper
) {

    public <T> Reservation createReservation(T data, Function<T, Reservation> converter) {
        var reservation = converter.apply(data);
        validatorHelper.validate(() -> reservation);
        reservationRepository.save(reservation);
        return reservation;
    }
}
