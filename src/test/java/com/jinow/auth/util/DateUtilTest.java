package com.jinow.auth.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

class DateUtilTest {

    @Test
    public void date_test() {
        System.out.println(ZoneId.systemDefault());
        Date date = new Date();
        System.out.println(date.toString());
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.stream().filter(zoneId -> zoneId.contains("Aust")).forEach(zoneId -> {
            //System.out.println(zoneId);
        });

        LocalDateTime localDateTime = date
                .toInstant()
                .atZone(ZoneId.of("Australia/Sydney"))
                .toLocalDateTime();

        System.out.println(localDateTime);
        localDateTime.atZone(ZoneId.systemDefault());
        System.out.println(date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

    }
}