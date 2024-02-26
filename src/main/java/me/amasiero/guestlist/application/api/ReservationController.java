package me.amasiero.guestlist.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateRequest;
import me.amasiero.guestlist.domain.service.dto.create.ReservationCreateResponse;
import me.amasiero.guestlist.domain.service.dto.list.GuestListResponse;
import me.amasiero.guestlist.domain.service.dto.update.ReservationUpdateRequest;
import me.amasiero.guestlist.domain.service.dto.update.ReservationUpdateResponse;
import me.amasiero.guestlist.domain.service.ports.input.ReservationService;

@RestController
@RequestMapping("/api/v1")
public record ReservationController(
    ReservationService reservationService
) {
    @PostMapping("/guest_list/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReservationCreateResponse> create(
        @PathVariable("name") String name,
        @RequestBody ReservationCreateRequest request
    ) {
        var created = reservationService.createReservation(request.toBuilder()
                                                                  .name(name)
                                                                  .build());
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(created);
    }

    @GetMapping("/guest_list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GuestListResponse> list() {
        var list = reservationService.listGuests();
        return ResponseEntity.status(HttpStatus.OK)
                             .body(list);
    }

    @PutMapping("/guests/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReservationUpdateResponse> update(
        @PathVariable("name") String name,
        @RequestBody ReservationUpdateRequest request
    ) {
        var updated = reservationService.updateReservation(request.toBuilder()
                                                                  .name(name)
                                                                  .build());
        return ResponseEntity.status(HttpStatus.OK)
                             .body(updated);
    }
}
