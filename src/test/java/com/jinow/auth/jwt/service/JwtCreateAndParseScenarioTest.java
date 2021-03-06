package com.jinow.auth.jwt.service;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jinow.auth.AuthDemoApplication;
import com.jinow.auth.annotation.SlowTest;
import com.jinow.auth.config.TimingExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
                () -> assertEquals(decodedJWT.getIssuer(), "jinow"),
                () -> assertEquals(decodedJWT.getClaim("memberId").asString(), "ca98047"),
                () -> assertEquals(decodedJWT.getClaim("memberName").asString(), "최진원"),
                () -> assertNotNull(decodedJWT.getIssuedAt())
        );
    }
}