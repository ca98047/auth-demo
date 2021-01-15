package com.jinow.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtServiceImpl implements JwtService {

    private final String hmacSecretKey = "jinowKey";
    private final String issuer = "jinow";

    @Override
    public String createJwtToken(String memberId, String memberName) {
        Algorithm algorithm = Algorithm.HMAC256(hmacSecretKey);

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("memberId", memberId)
                .withClaim("memberName", memberName)
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
