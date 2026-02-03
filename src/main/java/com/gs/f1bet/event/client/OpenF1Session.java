package com.gs.f1bet.event.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record OpenF1Session(
        @JsonProperty("meeting_key")
        Integer meetingKey,
        @JsonProperty("session_key")
        Integer sessionKey,
        @JsonProperty("session_type")
        String sessionType,
        @JsonProperty("session_name")
        String sessionName,
        @JsonProperty("year")
        Integer year,
        @JsonProperty("location")
        String location,
        @JsonProperty("gmt_offset")
        String gmtOffset,
        @JsonProperty("date_end")
        ZonedDateTime dateEnd,
        @JsonProperty("date_start")
        ZonedDateTime dateStart,
        @JsonProperty("circuit_key")
        Integer circuitKey,
        @JsonProperty("circuit_short_name")
        String circuitShortName,
        @JsonProperty("country_key")
        Integer countryKey,
        @JsonProperty("country_code")
        String countryCode,
        @JsonProperty("country_name")
        String countryName
) {
}
