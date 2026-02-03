package com.gs.f1bet.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record DriverDto(
        @Schema(example = "1")
        @JsonProperty("driver_number")
        Integer driverNumber,

        @Schema(example = "Max VERSTAPPEN")
        @JsonProperty("full_name")
        String fullName,

        @Schema(example = "3")
        @JsonProperty("odd")
        Integer odd
) {
}
