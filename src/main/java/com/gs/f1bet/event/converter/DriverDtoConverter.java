package com.gs.f1bet.event.converter;

import com.gs.f1bet.event.client.OpenF1Driver;
import com.gs.f1bet.event.dto.DriverDto;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DriverDtoConverter implements Function<OpenF1Driver, DriverDto> {

    @Override
    public DriverDto apply(OpenF1Driver driver) {
        return DriverDto.builder()
                .driverNumber(driver.driverNumber())
                .fullName(driver.fullName())
                .odd(RandomUtils.insecure().randomInt(2, 5))
                .build();
    }
}
