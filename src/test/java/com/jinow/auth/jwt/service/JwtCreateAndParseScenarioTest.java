package com.jinow.auth.jwt.service;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jinow.auth.AuthDemoApplication;
import com.jinow.auth.annotation.IntegrationTest;
import com.jinow.auth.config.TimingExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = AuthDemoApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TimingExtension.class)
public class JwtCreateAndParseScenarioTest {

    @Autowired
    JwtService jwtService;
    private String token = "";

    @IntegrationTest
    @Order(1)
    void 비어있는_토큰은_복호화_되지않음() {
        assertThrows(JWTDecodeException.class, () -> {
            DecodedJWT decodedJWT = jwtService.decodeToken(token);
        });

    }

    @IntegrationTest
    @Order(2)
    public void 토큰생성() throws InterruptedException {
        token = jwtService.createJwtToken("ca98047", "최진원");
    }

    @IntegrationTest
    @Order(3)
    void 토큰생성후검증하기() {

        DecodedJWT decodedJWT = jwtService.decodeToken(token);
        assertAll(
                () -> log.warn("---test start ---"),
                () -> assertEquals(decodedJWT.getIssuer(), "jinow"),
                () -> assertEquals(decodedJWT.getClaim("memberId").asString(), "ca98047"),
                () -> assertEquals(decodedJWT.getClaim("memberName").asString(), "최진원"),
                () -> assertNotNull(decodedJWT.getIssuedAt())
        );
    }
}