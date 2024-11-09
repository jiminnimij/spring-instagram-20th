package com.ceos20.instagram.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

import static io.jsonwebtoken.Jwts.SIG.HS256;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
public class JwtUtil {
    private static final int ACCESS_TOKEN_VALIDITY = 30 * 60 * 1000;
    private static final int REFRESH_TOKEN_VALIDITY = 60 * 60 * 1000;

    private final SecretKey secretKey;

    public JwtUtil(@Value("${spring.jwt.secret}") final String secret) {
        final String algorithm = HS256.key()
                .build()
                .getAlgorithm();
        this.secretKey = new SecretKeySpec(secret.getBytes(UTF_8), algorithm);
    }

    public String generateAccessToken(String nickname) {
        return Jwts.builder()
                .claim("nickname", nickname)
                .claim("type", "access")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(secretKey)
                .compact();
    }
    public String generateRefreshToken(String nickname) {
        return Jwts.builder()
                .claim("nickname", nickname)
                .claim("type", "refresh")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(secretKey)
                .compact();
    }

    public String getType(final String token) {
        return getPayload(token)
                .get("type", String.class);
    }

    public long getExpiration(final String token) {
        return getPayload(token)
                .getExpiration()
                .getTime();
    }

    public String getNickname(final String token) {
        return getPayload(token)
                .get("nickname", String.class);
    }

    public boolean isExpired(final String token) {
        return getPayload(token)
                .getExpiration()
                .before(new Date());
    }

    public boolean isEqualToRefreshToken(final String token) {
        return "refresh".equals(getType(token));
    }

    public boolean isEqualToAccessToken(final String token) {
        return "access".equals(getType(token));
    }

    private Claims getPayload(final String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

