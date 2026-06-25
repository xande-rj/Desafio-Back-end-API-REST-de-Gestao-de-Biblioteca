package com.biblioteca.security;

import com.biblioteca.domain.enuns.Roles;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private final String SECRET_KEY="my-super-secure-secret-key-for-jwt-signing-12345";
    private static final long PERIOD = 15*60*1000;

    public String generateToken (String idUser, Roles roles){
        return Jwts.builder()
                .setSubject(idUser)
                .setSubject(roles.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+PERIOD))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}
