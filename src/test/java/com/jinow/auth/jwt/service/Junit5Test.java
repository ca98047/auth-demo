package com.jinow.auth.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

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

        assumingThat(false,
                () -> assertTrue(false));

        assertTrue(true);
    }
}
