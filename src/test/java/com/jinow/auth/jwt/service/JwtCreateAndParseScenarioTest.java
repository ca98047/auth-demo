package com.jinow.auth.jwt.service;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jinow.auth.AuthDemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = AuthDemoApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JwtCreateAndParseScenarioTest {

    @Autowired
    JwtService jwtService;
    private String token = "";

    @Test
    @Order(1)
    void 비어있는_토큰은_복호화_되지않음() {
        assertThrows(JWTDecodeException.class, () -> {
            DecodedJWT decodedJWT = jwtService.parseToken(token);
        });
    }

    @Test
    @Order(2)
    public void 토큰생성() {
        token = jwtService.createJwtToken("ca98047", "최진원");
    }

    @Test
    @Order(3)
    void 토큰검증하기() {

        DecodedJWT decodedJWT = jwtService.parseToken(token);
        assertAll(
                () -> log.warn("---test start ---"),
                () -> assertEquals(decodedJWT.getIssuer(), "jinow"),
                () -> assertEquals(decodedJWT.getClaim("memberId").asString(), "ca98047"),
                () -> assertEquals(decodedJWT.getClaim("memberName").asString(), "최진원"),
                () -> assertNotNull(decodedJWT.getIssuedAt())
        );
    }
}