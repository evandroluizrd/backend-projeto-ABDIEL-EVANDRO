package com.example.weatherapi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "segredo123segredo123segredo123segredo123"; // 32+ chars
    private static final long EXPIRATION_TIME = 86400000; // 1 dia
    private static final Key CHAVE = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(CHAVE, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(CHAVE).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
