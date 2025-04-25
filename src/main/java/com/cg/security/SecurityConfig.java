package com.cg.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
//    private final JwtAuthenticationFilter jwtFilter;
//    private final CustomUserDetailsService customUserDetailsService;
//
//    @Autowired
//    public SecurityConfig(JwtAuthenticationFilter jwtFilter, CustomUserDetailsService customUserDetailsService) {
//        this.jwtFilter = jwtFilter;
//        this.customUserDetailsService = customUserDetailsService;
//    }

    
    
    @Autowired
    @Lazy // Lazy load JwtAuthenticationFilter to avoid circular dependency
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public SecurityConfig() {
    }
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Using BCrypt for password hashing
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/clients/register", "/api/clients/signin","/api/clients/signout").permitAll()  // Public endpoints

                    .requestMatchers("/authenticate").permitAll() // Allow open access to login endpoint
                    .requestMatchers("/clients/**").hasRole("CLIENT") // Client can access only client-related endpoints
                    .requestMatchers("/engineer/**").hasRole("ENGINEER") // Engineer can access only engineer-related endpoints
                    .requestMatchers("/admin/**").hasRole("ADMIN") // Admin can access only admin-related endpoints
                    .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before the UsernamePasswordAuthenticationFilter
                .build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
