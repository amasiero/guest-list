package me.amasiero.guestlist.domain.service.ports.input;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.amasiero.guestlist.domain.service.dto.mock.GuestDataMock;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;
import me.amasiero.guestlist.domain.service.util.GuestHelper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {
    @Mock
    protected GuestRepository repository;
    @Mock
    protected GuestHelper helper;
    protected GuestService service;

    @BeforeEach
    public void setUp() {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            helper = new GuestHelper(factory.getValidator());
            service = new GuestServiceImpl(repository, helper);
        }
    }

    @Nested
    @DisplayName("when creating a guest")
    class CreateCreateGuest {

        @Test
        @DisplayName("should successfully create a guest")
        public void shouldCreateAGuest() {
            when(repository.save(any(me.amasiero.guestlist.domain.service.dto.CreateGuest.class))).thenReturn(GuestDataMock.build());

            var result = service.createGuest(GuestDataMock.build());

            assertFalse(result.isEmpty());
            assertNotNull(result);
        }

        @Test
        @DisplayName("should not create a guest with null data")
        public void shouldNotCreateGuestWithNullData() {
            assertThrows(IllegalArgumentException.class, () -> service.createGuest(null));
        }

        @Test
        @DisplayName("should not create a guest without name")
        public void shouldNotCreateAGuestWithoutName() {
            var invalidGuest = GuestDataMock.buildWithName(null);
            assertThrows(ConstraintViolationException.class, () -> service.createGuest(invalidGuest));
        }

        @Test
        @DisplayName("should not create a guest without table")
        public void shouldNotCreateAGuestWithoutTable() {
            var invalidGuest = GuestDataMock.buildWithTable(null);
            assertThrows(ConstraintViolationException.class, () -> service.createGuest(invalidGuest));
        }

        @Test
        @DisplayName("should not create a guest without a valid table")
        public void shouldNotCreateAGuestWithoutAValidTable() {
            var invalidGuest = GuestDataMock.buildWithTable(0);
            assertThrows(ConstraintViolationException.class, () -> service.createGuest(invalidGuest));
        }

        @Test
        @DisplayName("should not create a guest without accompanying guests")
        public void shouldNotCreateAGuestWithoutAccompanyingGuests() {
            var invalidGuest = GuestDataMock.buildWithAccompanyingGuests(null);
            assertThrows(ConstraintViolationException.class, () -> service.createGuest(invalidGuest));
        }

        @Test
        @DisplayName("should not create a guest without a valid accompanying guests")
        public void shouldNotCreateAGuestWithoutAValidAccompanyingGuests() {
            var invalidGuest = GuestDataMock.buildWithAccompanyingGuests(0);
            assertThrows(ConstraintViolationException.class, () -> service.createGuest(invalidGuest));
        }
    }

}
