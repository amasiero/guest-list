package me.amasiero.guestlist.domain.service.dto.list;

import java.util.List;

public record GuestListResponse<T>(List<T> guests) {
}
