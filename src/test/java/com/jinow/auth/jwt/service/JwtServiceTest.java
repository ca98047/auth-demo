package com.jinow.auth.jwt.service;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AuthDemoApplication.class)
@ExtendWith(TimingExtension.class)
@Slf4j
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
    void expire된_토큰은_jwt검증실패() {
        TokenExpiredException tokenExpiredException = assertThrows(TokenExpiredException.class, () -> {
            jwtService.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqaW5vdyIsIm1lbWJlck5hbWUiOiLstZzsp4Tsm5AiLCJleHAiOjE2MTE0NzA1MjksImlhdCI6MTYxMTQ3MDQ5OSwibWVtYmVySWQiOiJjYTk4MDQ3In0.efBWO85Nvma71SwpXQAdFvvKwrImy9Qdvk1em8wOJgk");
        });

        log.warn(tokenExpiredException.getMessage());
    }

    @Test
    void 토큰검증_성공() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqaW5vdyIsIm1lbWJlck5hbWUiOiLstZzsp4Tsm5AiLCJpYXQiOjE2MTA4NjE4NjksIm1lbWJlcklkIjoiY2E5ODA0NyJ9.k5zjVjO8-IIE8FFy2fKAUKVQw7jUYGSFNWKuyyyOOH4";
        DecodedJWT decodedJWT = jwtService.parseToken(token);
        assertEquals(decodedJWT.getIssuer(), "jinow");
    }

    @ParameterizedTest(name = "토큰 암복호화 테스트 memberId = {0}, memberName = {1}")
    @CsvSource({"ca98047, 최진원", "jinow.c, 진원최"})
    public void 토큰_암복호화(String memberId, String memberName) {
        String jwtToken = jwtService.createJwtToken(memberId, memberName);

        DecodedJWT decodedJWT = jwtService.parseToken(jwtToken);
        assertAll(
                () -> assertEquals(decodedJWT.getIssuer(), "jinow"),
                () -> assertEquals(decodedJWT.getClaim("memberId").asString(), memberId),
                () -> assertEquals(decodedJWT.getClaim("memberName").asString(), memberName),
                () -> assertNotNull(decodedJWT.getIssuedAt())
        );
    }

    @SlowTest
    public void 오래_실행되는_테스트로직() throws InterruptedException {
        Thread.sleep(2000);
    }

}