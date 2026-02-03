package com.gs.f1bet.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.Collection;

@Builder
public record EventDto(
        @Schema(example = "9135")
        @JsonProperty("event_key")
        Integer eventKey,

        @Schema(example = "Qualifying")
        @JsonProperty("event_type")
        String eventType,

        @Schema(example = "Qualifying")
        @JsonProperty("event_name")
        String eventName,

        @Schema(example = "2023")
        @JsonProperty("year")
        Integer year,

        @Schema(example = "Spa-Francorchamps")
        @JsonProperty("location")
        String location,

        @Schema(example = "2023-07-28T16:00:00Z")
        @JsonProperty("date_end")
        ZonedDateTime dateEnd,

        @Schema(example = "2023-07-28T15:00:00Z")
        @JsonProperty("date_start")
        ZonedDateTime dateStart,

        @Schema(example = "Belgium")
        @JsonProperty("country_name")
        String countryName,

        @ArraySchema(schema = @Schema(implementation = DriverDto.class))
        Collection<DriverDto> drivers) {
}
