package com.cg.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JWtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get the token from the "Authorization" header
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix

            // Validate the token
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);

                // Set the authentication in the Spring Security context
                UsernamePasswordAuthenticationToken authenticationToken = 
                        new UsernamePasswordAuthenticationToken(username, null, null); // No credentials, just authentication
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response); // Continue the filter chain
    }

	
}
