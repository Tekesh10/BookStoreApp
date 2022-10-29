package com.example.bookstoreapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTToken {
    private static final String TOKEN = "ACE";
    public String createToken(long id) {
        return JWT.create().withClaim("user_id", id).sign(Algorithm.HMAC256(TOKEN));
    }
    public long decodeToken(String token) {
        long userId = 0;
        if (token != null) {
            userId = JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token).getClaim("user_id").asInt();
        }
        return userId;
    }
}