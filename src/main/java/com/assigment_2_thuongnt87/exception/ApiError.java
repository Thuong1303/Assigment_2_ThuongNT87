package com.assigment_2_thuongnt87.exception;
import java.time.Instant;
import java.util.Map;

public record ApiError(
    Instant timestamp,
    String code,
    String message,
    Map<String, Object> details
) {}
