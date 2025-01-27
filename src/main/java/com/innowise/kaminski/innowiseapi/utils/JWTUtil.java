package com.innowise.kaminski.innowiseapi.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${security.jwt.secret-key}")
    private String SECRET;

    @Value("${security.jwt.expiration-time}")
    private Long EXPIRATIONTIME;


    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token");
        } catch (Exception e) {
            System.out.println("Token validation failed");
        }
        return false;
    }

    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
