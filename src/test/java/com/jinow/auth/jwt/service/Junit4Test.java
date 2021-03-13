package com.jinow.auth.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class Junit4Test {

    @Before
    public void before() {
        log.warn("before");
    }

    @After
    public void after() {
        log.warn("after");
    }

    @Test
    public void test() {
        int num1 = 1;
        int num2 = 2;
        assertTrue(num1 + " > " + num2, num2<num1);
        log.warn("hello");
    }

    @Test
    public void test2() {
        log.warn("hello2");
    }
}
