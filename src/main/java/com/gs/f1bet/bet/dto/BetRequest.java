package com.gs.f1bet.bet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record BetRequest(
        @Schema(example = "9135")
        @JsonProperty("event_key")
        String eventKey,

        @Schema(example = "1")
        @JsonProperty("driver_number")
        String driverNumber,

        @Schema(example = "25")
        @JsonProperty("amount")
        BigDecimal amount) {
}
