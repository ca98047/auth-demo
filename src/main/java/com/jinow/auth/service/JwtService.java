package com.jinow.auth.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtService {

    String createJwtToken(String memberId, String memberName);

    DecodedJWT parseToken(String jwtToken);
}
