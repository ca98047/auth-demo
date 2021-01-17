package com.jinow.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.hmac-secret}")
    private String hmacSecretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Override
    public String createJwtToken(String memberId, String memberName) {
        Algorithm algorithm = Algorithm.HMAC256(hmacSecretKey);

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("memberId", memberId)
                .withClaim("memberName", memberName)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    @Override
    public DecodedJWT parseToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(hmacSecretKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();

        return jwtVerifier.verify(jwtToken);
    }
}
