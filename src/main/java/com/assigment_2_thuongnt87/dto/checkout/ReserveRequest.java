package com.assigment_2_thuongnt87.dto.checkout;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;

public record ReserveRequest(
    @NotNull UUID cartToken,
    Integer holdMinutes
) {}
