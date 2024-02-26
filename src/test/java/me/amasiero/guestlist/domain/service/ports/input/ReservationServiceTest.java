package me.amasiero.guestlist.domain.service.ports.input;

import java.util.List;
import java.util.function.Function;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.amasiero.guestlist.domain.service.dto.update.ReservationUpdateRequest;
import me.amasiero.guestlist.domain.service.handler.ReservationHandler;
import me.amasiero.guestlist.domain.service.mock.GuestArrivedDtoDataMock;
import me.amasiero.guestlist.domain.service.mock.GuestDtoDataMock;
import me.amasiero.guestlist.domain.service.mock.ReservationCreateRequestDataMock;
import me.amasiero.guestlist.domain.service.mock.ReservationDataMock;
import me.amasiero.guestlist.domain.service.mock.ReservationUpdateRequestDataMock;
import me.amasiero.guestlist.domain.service.util.ValidatorHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @Mock
    ReservationHandler handler;
    @Mock
    ValidatorHelper helper;
    ReservationService service;

    @BeforeEach
    void setUp() {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            var validator = factory.getValidator();
            helper = new ValidatorHelper(validator);
            service = new ReservationServiceImpl(handler, helper);
        }
    }

    @Nested
    @DisplayName("when creating a reservation")
    class CreateReservation {

        @Test
        @DisplayName("should successfully create a reservation")
        void shouldCreateAReservation() {
            var guestRequest = ReservationCreateRequestDataMock.build();
            var reservation = ReservationDataMock.build();
            when(handler.createReservation(eq(guestRequest), any(Function.class))).thenReturn(reservation);

            var response = service.createReservation(guestRequest);

            assertNotNull(response);
            assertEquals("John Doe", response.name());
        }

        @Test
        @DisplayName("should not create a reservation with null data")
        void shouldNotCreateReservationWithNullData() {
            assertThrows(IllegalArgumentException.class, () -> service.createReservation(null));
        }

        @Test
        @DisplayName("should not create a reservation without name")
        void shouldNotCreateReservationWithoutName() {
            var guestRequestWithoutName = ReservationCreateRequestDataMock.buildWithName(null);
            assertThrows(ConstraintViolationException.class, () -> service.createReservation(guestRequestWithoutName));
        }

        @Test
        @DisplayName("should not create a reservation with invalid name")
        void shouldNotCreateReservationWithInvalidName() {
            var guestRequestWithInvalidName = ReservationCreateRequestDataMock.buildWithName("");
            assertThrows(ConstraintViolationException.class, () -> service.createReservation(guestRequestWithInvalidName));
        }

        @Test
        @DisplayName("should not create a reservation without table")
        void shouldNotCreateReservationWithoutTable() {
            var guestRequestWithoutTable = ReservationCreateRequestDataMock.buildWithTable(null);
            assertThrows(ConstraintViolationException.class, () -> service.createReservation(guestRequestWithoutTable));
        }

        @Test
        @DisplayName("should not create a reservation with invalid table")
        void shouldNotCreateReservationWithInvalidTable() {
            var guestRequestWithInvalidTable = ReservationCreateRequestDataMock.buildWithTable(0);
            assertThrows(ConstraintViolationException.class, () -> service.createReservation(guestRequestWithInvalidTable));
        }

        @Test
        @DisplayName("should not create a reservation without accompanying guests")
        void shouldNotCreateReservationWithoutAccompanyingGuests() {
            var guestRequestWithoutAccompanyingGuests = ReservationCreateRequestDataMock.buildWithAccompanyingGuests(null);
            assertThrows(ConstraintViolationException.class, () -> service.createReservation(guestRequestWithoutAccompanyingGuests));
        }

        @Test
        @DisplayName("should not create a reservation with invalid accompanying guests")
        void shouldNotCreateReservationWithInvalidAccompanyingGuests() {
            var guestRequestWithInvalidAccompanyingGuests = ReservationCreateRequestDataMock.buildWithAccompanyingGuests(-1);
            assertThrows(ConstraintViolationException.class, () -> service.createReservation(guestRequestWithInvalidAccompanyingGuests));
        }
    }

    @Nested
    @DisplayName("when listing guests")
    class ListGuests {

        @Test
        @DisplayName("returns all guests")
        void returnsAllGuests() {
            var guests = List.of(GuestDtoDataMock.build(), GuestDtoDataMock.build());
            when(handler.listGuests()).thenReturn(guests);

            var result = service.listGuests();

            assertEquals(guests.size(), result.guests().size());
            verify(handler).listGuests();
        }
    }

    @Nested
    @DisplayName("when updating a reservation")
    class UpdateReservation {

        @Test
        @DisplayName("updates the reservation successfully")
        void updatesReservationSuccessfully() {
            var reservationUpdateRequest = ReservationUpdateRequestDataMock.build();
            doNothing().when(handler).updateReservation(any(ReservationUpdateRequest.class), any(Function.class));

            var result = service.updateReservation(reservationUpdateRequest);

            assertEquals(reservationUpdateRequest.name(), result.name());
            verify(handler).updateReservation(any(ReservationUpdateRequest.class), any(Function.class));
        }
    }

    @Nested
    @DisplayName("when listing arrivals")
    class ListOfArrivals {

        @Test
        @DisplayName("returns all arrived guests")
        void returnsAllArrivedGuests() {
            var guests = List.of(GuestArrivedDtoDataMock.build(), GuestArrivedDtoDataMock.build());
            when(handler.listOfArrivals()).thenReturn(guests);

            var result = service.listOfArrivals();

            assertEquals(guests.size(), result.guests().size());
            verify(handler).listOfArrivals();
        }
    }

    @Nested
    @DisplayName("when a guest leaves")
    class GuestLeave {

        @Test
        @DisplayName("removes the guest successfully")
        void removesGuestSuccessfully() {
            var guestName = "John Doe";
            doNothing().when(handler).guestLeave(any(String.class));

            service.guestLeave(guestName);

            verify(handler).guestLeave(any(String.class));
        }
    }

}
