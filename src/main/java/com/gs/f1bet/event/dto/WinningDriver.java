package com.gs.f1bet.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record WinningDriver(
        @Schema(example = "1")
        @JsonProperty("driver_number")
        String driverNumber,

        @Schema(example = "3")
        @JsonProperty("odd")
        Integer odd
) {
}
