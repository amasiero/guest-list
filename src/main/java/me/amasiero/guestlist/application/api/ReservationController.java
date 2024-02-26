package me.amasiero.guestlist.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import me.amasiero.guestlist.domain.service.dto.list.GuestArrivedDto;
import me.amasiero.guestlist.domain.service.dto.list.GuestDto;
import me.amasiero.guestlist.domain.service.dto.list.GuestListResponse;
import me.amasiero.guestlist.domain.service.dto.seat.EmptySeatsResponse;
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
    public ResponseEntity<GuestListResponse<GuestDto>> list() {
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

    @GetMapping("/guests")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GuestListResponse<GuestArrivedDto>> listOfArrivals() {
        var list = reservationService.listOfArrivals();
        return ResponseEntity.status(HttpStatus.OK)
                             .body(list);
    }

    @DeleteMapping("/guests/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> guestLeave(@PathVariable("name") String name) {
        reservationService.guestLeave(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/seats_empty")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmptySeatsResponse> retrieveEmptySeats() {
        var seatsEmpty = reservationService.retrieveEmptySeats();
        return ResponseEntity.status(HttpStatus.OK)
                             .body(seatsEmpty);
    }
}
