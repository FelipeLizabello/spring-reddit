package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExp;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExp))
                .signWith(getSigningKey())
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction){
        return claimsFunction.apply(
                Jwts.parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
        );
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token){
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return Objects.equals(username, userDetails.getUsername()) &&
                !extractClaim(token, Claims::getExpiration).before(new Date());

    }

}
