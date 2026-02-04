package com.gs.f1bet.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record EventOutcomeDto(
        @Schema(example = "9135")
        @JsonProperty("event_key")
        String eventKey,

        @Schema(example = "1")
        @JsonProperty("driver_number")
        String driverNumber,

        @Schema(example = "3")
        @JsonProperty("winners")
        Integer winners,

        @Schema(example = "2")
        @JsonProperty("losers")
        Integer losers
) {
}
