package com.jinow.auth.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jinow.auth.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.hmac-secret}")
    private String hmacSecretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.token-expire-seconds}")
    private Long tokenExpireSeconds;

    public JwtServiceImpl(String hmacSecretKey, String issuer, Long tokenExpireSeconds) {
        this.hmacSecretKey = hmacSecretKey;
        this.issuer = issuer;
        this.tokenExpireSeconds = tokenExpireSeconds;
    }

    @Override
    public String createJwtToken(String memberId, String memberName) {
        Algorithm algorithm = Algorithm.HMAC256(hmacSecretKey);
        LocalDateTime now = LocalDateTime.now();

        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(DateUtil.convertToDate(now))
                .withExpiresAt(DateUtil.convertToDate(now.plusSeconds(tokenExpireSeconds)))
                .withClaim("memberId", memberId)
                .withClaim("memberName", memberName)
                .sign(algorithm);
    }

    @Override
    public DecodedJWT decodeToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(hmacSecretKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();

        return jwtVerifier.verify(jwtToken);
    }
}
