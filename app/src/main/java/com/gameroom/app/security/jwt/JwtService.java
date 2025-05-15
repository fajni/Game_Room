package com.gameroom.app.security.jwt;

import com.gameroom.app.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    // Secret value for HMAC for signing and verification JWT token.
    public static final String SECRET = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";

    public String generateToken(User user) {

        // claims are meta data
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().getRoleName());
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .claims()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .add(claims)
                .and()
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {

        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET);

        return Keys.hmacShaKeyFor(keyBytes);

        /*
        try {

            SecretKey key = KeyGenerator.getInstance("HmacSHA256").generateKey();

            return key;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        */
    }

    public Claims extractAllClaims(String token) {

        try {
            JwtParser parser = Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build();

            Claims claims = parser
                    .parseSignedClaims(token)
                    .getPayload();

            return claims;

        } catch (SignatureException e) {

            System.err.println("Invalid token signature (JwtService) - " + e.getMessage());
            throw new SignatureException(e.getMessage());

        } catch (Exception e) {

            System.err.println("Extraction Claims Error (JwtService) - " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Boolean isTokenExpired(String token) {

        Claims claims = extractAllClaims(token);

        Date expirationTime = claims.getExpiration();

        if(expirationTime.before(new Date())) {
            return true;
        }

        return false;
    }

    public Boolean validateToken(String token, User user) {

        Claims claims = extractAllClaims(token);

        if (claims == null)
            return false;

        return (claims.getSubject().equals(user.getUsername()) && !isTokenExpired(token));
    }
}
