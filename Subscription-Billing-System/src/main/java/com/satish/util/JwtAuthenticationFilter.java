package com.satish.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    private final String SECRET = "mzAbMaV0hAmfwZfq+cDeZtWUgirwmWwkU7azikcyrGQ=";

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException{
        String header = request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer")){
            String token=header.substring(7);
            Claims claims= Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            System.out.println(role);

            List<GrantedAuthority> authorities =
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(email, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
