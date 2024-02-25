package me.amasiero.guestlist.domain.service.ports.input;

import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.amasiero.guestlist.domain.service.GuestServiceImpl;
import me.amasiero.guestlist.domain.service.dto.Guest;
import me.amasiero.guestlist.domain.service.dto.mock.GuestDataMock;
import me.amasiero.guestlist.domain.service.ports.output.GuestRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @Mock
    protected GuestRepository guestRepository;

    protected GuestService guestService;

    @BeforeEach
    public void setUp() {
        guestService = new GuestServiceImpl(guestRepository);
    }

    @Nested
    @DisplayName("when creating a guest")
    class CreateGuest {

        @Test
        @DisplayName("should successfully create a guest")
        public void shouldCreateAGuest() {
            when(guestRepository.save(any(Guest.class))).thenReturn(GuestDataMock.build());

            Guest result = guestService.createGuest(GuestDataMock.build());

            assertNotNull(result);
        }

        @Test
        @DisplayName("should not create a guest with null data")
        public void shouldNotCreateGuestWithNullData() {
            assertThrows(IllegalArgumentException.class, () -> guestService.createGuest(null));
        }

        @Test
        @DisplayName("should not create a guest without name")
        public void shouldNotCreateAGuestWithoutName() {
            Guest invalidGuest = GuestDataMock.buildWithName("");
            when(guestRepository.save(any(Guest.class))).thenReturn(invalidGuest);

            assertThrows(ConstraintViolationException.class, () -> guestService.createGuest(invalidGuest));
        }
    }

}
