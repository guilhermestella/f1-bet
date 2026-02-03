package com.gs.f1bet.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class EventControllerTest {

    @Autowired MockMvc mockMvc;

    @Test
    @Disabled("A Mock server would be preferable. Not enough time.")
    void getEvents() throws Exception {
        // given
        var req = get("/events?year=2023&country_name=Belgium&event_type=Qualifying");

        // when
        var get = mockMvc.perform(req);

        // then
        var expected = Files.readString(
                Path.of(getClass()
                        .getClassLoader()
                        .getResource("json/events-with-drivers.json")
                        .toURI()));

        get.andExpect(status().isOk()).andExpect(content().json(expected));
    }
}
