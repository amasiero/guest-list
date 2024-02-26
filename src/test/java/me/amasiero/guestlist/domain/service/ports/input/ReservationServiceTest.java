package me.amasiero.guestlist.domain.service.ports.input;

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

import me.amasiero.guestlist.domain.service.handler.ReservationHandler;
import me.amasiero.guestlist.domain.service.mock.ReservationCreateRequestDataMock;
import me.amasiero.guestlist.domain.service.mock.ReservationDataMock;
import me.amasiero.guestlist.domain.service.util.ValidatorHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

}
