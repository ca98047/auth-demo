package com.jinow.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


@Slf4j
public class JwtServiceTest {
    JwtService jwtService;

    @BeforeClass
    public void init() {
        jwtService = new JwtServiceImpl();
    }

    @Test
    public void 토큰생성() {
        String jwtToken = jwtService.createJwtToken("ca98047", "최진원");

        System.out.println(jwtToken);
    }

    @Test
    public void 토큰복호화_및_검증() {
        assertThrows(NullPointerException.class, () -> {
            jwtService.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqaW5vdyJ9.RLeJTDH4VJWX6iclq0F6DUkemHes8tXG9VHNvDMzbrQ");
        });
    }


    @Test
    public void 토큰_암복호화() {
        String jwtToken = jwtService.createJwtToken("ca98047", "jinow.c");

        DecodedJWT decodedJWT = jwtService.parseToken(jwtToken);
        System.out.println(decodedJWT.getIssuer());
        System.out.println(decodedJWT.getClaim("memberId").asString());
        System.out.println(decodedJWT.getClaim("memberName").asString());
        System.out.println(decodedJWT.getIssuedAt());
    }


}