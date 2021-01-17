package com.jinow.auth.jwt.service;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jinow.auth.AuthDemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = AuthDemoApplication.class)
public class JwtServiceTest {

    @Autowired
    JwtService jwtService;

    @BeforeAll
    static void init() {
        System.out.println("test start");
    }

    @BeforeEach
    public void beforeTest() {
    }

    @Test
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
    void 토큰검증_성공() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqaW5vdyIsIm1lbWJlck5hbWUiOiLstZzsp4Tsm5AiLCJpYXQiOjE2MTA4NjE4NjksIm1lbWJlcklkIjoiY2E5ODA0NyJ9.k5zjVjO8-IIE8FFy2fKAUKVQw7jUYGSFNWKuyyyOOH4";
        DecodedJWT decodedJWT = jwtService.parseToken(token);
        assertEquals(decodedJWT.getIssuer(), "jinow");
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