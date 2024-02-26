package me.amasiero.guestlist.domain.service.handler;

import java.util.List;
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
import me.amasiero.guestlist.domain.service.mock.GuestDataMock;
import me.amasiero.guestlist.domain.service.mock.GuestEntityDataMock;
import me.amasiero.guestlist.domain.service.mock.ReservationDataMock;
import me.amasiero.guestlist.domain.service.mock.TableEntityDataMock;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationHandlerTest {

    @Mock
    GuestRepository guestRepository;

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

    @Nested
    @DisplayName("when listing guests")
    class ListGuests {

        @Test
        @DisplayName("returns all guests")
        void returnsAllGuests() {
            var guests = List.of(GuestEntityDataMock.build(), GuestEntityDataMock.build());
            when(guestRepository.findAll()).thenReturn(guests);

            var result = reservationHandler.listGuests();

            assertEquals(guests.size(), result.size());
            verify(guestRepository).findAll();
        }
    }

    @Nested
    @DisplayName("when updating a reservation")
    class UpdateReservation {

        @Test
        @DisplayName("updates the reservation successfully")
        void updatesReservationSuccessfully() {
            var reservation = ReservationDataMock.build();
            var guestEntity = GuestEntityDataMock.build();
            when(guestRepository.getGuestEntity(any(Reservation.class))).thenReturn(guestEntity);

            reservationHandler.updateReservation(reservation, Function.identity());

            verify(guestRepository).getGuestEntity(any(Reservation.class));
            verify(guestRepository).save(any(GuestEntity.class));
        }

        @Test
        @DisplayName("throws exception when table is out of capacity")
        void throwsExceptionWhenTableOutOfCapacity() {
            var reservation = ReservationDataMock.buildWithGuest(GuestDataMock.buildWithAccompanyingGuests(5));
            var guestEntity = GuestEntityDataMock.build();
            when(guestRepository.getGuestEntity(any(Reservation.class))).thenReturn(guestEntity);

            assertThrows(TableOutOfCapacityException.class, () -> reservationHandler.updateReservation(reservation, Function.identity()));
        }
    }

    @Nested
    @DisplayName("when listing arrivals")
    class ListOfArrivals {

        @Test
        @DisplayName("returns all arrived guests")
        void returnsAllArrivedGuests() {
            var guests = List.of(GuestEntityDataMock.anGuestEntityWithTimeArrived("2022-03-01T10:00:00"), GuestEntityDataMock.anGuestEntityWithTimeArrived("2022-03-01T11:00:00"));
            when(guestRepository.findAll()).thenReturn(guests);

            var result = reservationHandler.listOfArrivals();

            assertEquals(guests.size(), result.size());
            verify(guestRepository).findAll();
        }
    }

    @Nested
    @DisplayName("when a guest leaves")
    class GuestLeave {

        @Test
        @DisplayName("removes the guest successfully")
        void removesGuestSuccessfully() {
            var guestEntity = GuestEntityDataMock.build();
            when(guestRepository.getGuestEntity(any(Reservation.class))).thenReturn(guestEntity);

            reservationHandler.guestLeave(guestEntity.getName());

            verify(guestRepository).getGuestEntity(any(Reservation.class));
            verify(guestRepository).delete(any(GuestEntity.class));
        }
    }
}
