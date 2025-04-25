package com.cg.security;


import com.cg.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JWtUtil jwtUtil;

    @PostMapping
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            // Authenticate the user based on username, password, and role
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername() + ":" + authRequest.getRole(),
                            authRequest.getPassword()
                    )
            );

            // Generate JWT Token
            String token = jwtUtil.generateToken(authentication);

            return new AuthResponse(token);

        } catch (Exception e) {
            throw new Exception("Invalid login credentials");
        }
    }
}
