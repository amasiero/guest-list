package me.amasiero.guestlist.domain.service.ports.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import me.amasiero.guestlist.domain.service.GuestServiceImpl;
import me.amasiero.guestlist.domain.service.dto.Guest;
import me.amasiero.guestlist.domain.service.dto.mock.GuestDataMock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @Spy
    protected GuestService guestService = new GuestServiceImpl();

    @Nested
    @DisplayName("when creating a guest")
    class CreateGuest {

        @Test
        @DisplayName("should successfully create a guest")
        public void shouldCreateAGuest() {
            Guest result = guestService.createGuest(GuestDataMock.build());
            assertNotNull(result);
        }
    }

}
