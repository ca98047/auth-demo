package com.jinow.auth.service;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class JwtServiceTest {
    JwtService jwtService;

    @BeforeAll
    static void init() {
        System.out.println("test start");
    }

    @BeforeEach
    public void beforeTest() {
        jwtService = new JwtServiceImpl();
    }

    @Test
    @Disabled
    public void 토큰생성() {
        String jwtToken = jwtService.createJwtToken("ca98047", "최진원");
        System.out.println(jwtToken);
    }

    @Test
    void 다른키로_발급받은_토큰은_jwt_검증실패() {
        assertThrows(SignatureVerificationException.class, () -> {
            jwtService.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqaW5vdyIsIm1lbWJlck5hbWUiOiLstZzsp4Tsm5AiLCJtZW1iZXJJZCI6ImNhOTgwNDcifQ.yJf03lbtbx0BSs46v6K6_O75hvv6rieIfyrrxx7dJM8");
        });
    }


    @Test
    public void 토큰_암복호화() {
        String memberId = "ca98047";
        String memberName = "jinow.c";
        String jwtToken = jwtService.createJwtToken("ca98047", memberName);

        DecodedJWT decodedJWT = jwtService.parseToken(jwtToken);
        assertEquals(decodedJWT.getIssuer(), "jinow");
        assertEquals(decodedJWT.getClaim("memberId").asString(), memberId);
        assertEquals(decodedJWT.getClaim("memberName").asString(), memberName);
        assertNotNull(decodedJWT.getIssuedAt());
    }


}