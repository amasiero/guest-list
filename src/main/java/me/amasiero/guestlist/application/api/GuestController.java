package me.amasiero.guestlist.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.amasiero.guestlist.application.api.dto.GuestCreatedResponse;
import me.amasiero.guestlist.domain.service.dto.Guest;
import me.amasiero.guestlist.domain.service.ports.input.GuestService;

@RestController
@RequestMapping("/api/v1")
public record GuestController(
    GuestService guestService
) {
    @PostMapping("/guest_list/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GuestCreatedResponse> create(
        @PathVariable("name") String name,
        @RequestBody Guest guest
    ) {
        var created = guestService.createGuest(guest.toBuilder()
                                                    .name(name)
                                                    .build());
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new GuestCreatedResponse(created));
    }
}
