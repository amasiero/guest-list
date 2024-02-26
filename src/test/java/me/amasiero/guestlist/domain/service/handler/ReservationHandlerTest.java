package me.amasiero.guestlist.domain.service.handler;

import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.amasiero.guestlist.data.entity.GuestEntity;
import me.amasiero.guestlist.domain.core.entity.Reservation;
import me.amasiero.guestlist.domain.core.exception.TableNotAvailableException;
import me.amasiero.guestlist.domain.core.exception.TableOutOfCapacityException;
import me.amasiero.guestlist.domain.core.valueobject.TableStatus;
import me.amasiero.guestlist.domain.service.mock.GuestEntityDataMock;
import me.amasiero.guestlist.domain.service.mock.ReservationDataMock;
import me.amasiero.guestlist.domain.service.mock.TableEntityDataMock;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;
import me.amasiero.guestlist.domain.service.util.ValidatorHelper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationHandlerTest {

    @Mock
    GuestRepository guestRepository;

    @Mock
    ValidatorHelper validatorHelper;

    @InjectMocks
    ReservationHandler reservationHandler;

    @Nested
    @DisplayName("when creating a reservation")
    class CreateReservation {

        @Test
        @DisplayName("with valid data returns reservation")
        void withValidData_returnsReservation() {
            var reservation = ReservationDataMock.build();
            var guestEntity = GuestEntityDataMock.build();
            when(guestRepository.getGuestEntity(any(Reservation.class))).thenReturn(guestEntity);
            when(guestRepository.save(any(GuestEntity.class))).thenReturn(reservation);
            when(guestRepository.hasTableAvailable()).thenReturn(true);

            reservationHandler.createReservation(reservation, Function.identity());

            verify(validatorHelper).validate(any());
            verify(guestRepository).getGuestEntity(any(Reservation.class));
            verify(guestRepository).save(any(GuestEntity.class));
            verify(guestRepository).hasTableAvailable();
        }

        @Test
        @DisplayName("with table out of capacity throws exception")
        void withTableOutOfCapacity_throwsException() {
            var reservation = ReservationDataMock.build();
            var guestEntity = GuestEntityDataMock.anGuestEntityWithAccompanyingGuests(5);
            when(guestRepository.getGuestEntity(any(Reservation.class))).thenReturn(guestEntity);

            assertThrows(TableOutOfCapacityException.class, () -> reservationHandler.createReservation(reservation, Function.identity()));
        }

        @Test
        @DisplayName("with table not available throws exception")
        void withTableNotAvailable_throwsException() {
            var reservation = ReservationDataMock.build();
            var guestEntity = GuestEntityDataMock.anGuestEntityWithTable(TableEntityDataMock.anTableEntityWithStatus(TableStatus.OCCUPIED));
            when(guestRepository.getGuestEntity(any(Reservation.class))).thenReturn(guestEntity);

            assertThrows(TableNotAvailableException.class, () -> reservationHandler.createReservation(reservation, Function.identity()));
        }

        @Test
        void withNoTablesAvailable_throwsException() {
            var reservation = ReservationDataMock.build();
            var guestEntity = GuestEntityDataMock.build();
            when(guestRepository.getGuestEntity(any(Reservation.class))).thenReturn(guestEntity);
            when(guestRepository.hasTableAvailable()).thenReturn(false);

            assertThrows(TableOutOfCapacityException.class, () -> reservationHandler.createReservation(reservation, Function.identity()));
        }
    }
}
