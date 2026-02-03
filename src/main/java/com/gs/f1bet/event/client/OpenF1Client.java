package com.gs.f1bet.event.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(
        name = "openf1",
        url = "https://api.openf1.org/v1")
public interface OpenF1Client {

    @GetMapping("/sessions")
    Collection<OpenF1Session> getSessions(
            @RequestParam("year") Integer year,
            @RequestParam("country_name") String countryName,
            @RequestParam("session_type") String sessionType);

    @GetMapping("/drivers")
    Collection<OpenF1Driver> getDrivers(
            @RequestParam("session_key") Integer sessionKey);
}
