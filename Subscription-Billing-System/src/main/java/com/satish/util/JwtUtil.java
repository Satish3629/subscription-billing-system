package com.satish.util;

import com.satish.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "mzAbMaV0hAmfwZfq+cDeZtWUgirwmWwkU7azikcyrGQ=";

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role",user.getRole().getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
