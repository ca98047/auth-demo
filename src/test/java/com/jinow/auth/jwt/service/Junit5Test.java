package com.jinow.auth.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Slf4j
public class Junit5Test {

    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    public void 반복_테스트() {
        log.warn("repeated");
    }

    @Test
    public void assumeTest() {
        assertTrue(true);

        //가정이 실패했기 때문에, 테스트코드가 실행되지 않는다.
        assumingThat(false,
                () -> assertTrue(false));

    }

    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithEnumSource(TemporalUnit unit) {
        log.warn(unit.toString());
        assertNotNull(unit);
    }

}
