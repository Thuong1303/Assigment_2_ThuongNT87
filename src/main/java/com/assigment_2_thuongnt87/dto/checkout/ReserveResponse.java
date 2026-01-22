package com.assigment_2_thuongnt87.dto.checkout;

import java.time.Instant;
import java.util.UUID;

public record ReserveResponse(
    UUID reservationToken,
    Instant expiresAt
) {}
