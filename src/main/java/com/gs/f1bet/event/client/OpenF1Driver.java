package com.gs.f1bet.event.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OpenF1Driver(
        @JsonProperty("meeting_key")
        Integer meetingKey,
        @JsonProperty("session_key")
        Integer sessionKey,
        @JsonProperty("driver_number")
        Integer driverNumber,
        @JsonProperty("broadcast_name")
        String broadcastName,
        @JsonProperty("full_name")
        String fullName,
        @JsonProperty("name_acronym")
        String nameAcronym,
        @JsonProperty("team_name")
        String teamName,
        @JsonProperty("team_colour")
        String teamColour,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @JsonProperty("headshot_url")
        String headshotUrl,
        @JsonProperty("country_code")
        String countryCode
) {
}
