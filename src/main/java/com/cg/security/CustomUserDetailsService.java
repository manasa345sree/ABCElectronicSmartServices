package com.cg.security;


import com.cg.entity.Admin;
import com.cg.entity.Client;
import com.cg.entity.Engineer;
import com.cg.repository.AdminRepository;
import com.cg.repository.ClientRepository;
import com.cg.repository.EngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameWithRole) throws UsernameNotFoundException {
        // Format: "username:role"
        String[] parts = usernameWithRole.split(":");
        if (parts.length != 2) {
            throw new UsernameNotFoundException("Invalid login format.");
        }

        String username = parts[0];
        String role = parts[1].toLowerCase();

        switch (role) {
            case "admin":
                Admin admin = adminRepository.findByAdminId(Integer.parseInt(username)) // Find by adminId
                        .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
                return new User(admin.getAdminId().toString(), admin.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));

            case "client":
                Client client = clientRepository.findByClientId(username) // Find by clientId
                        .orElseThrow(() -> new UsernameNotFoundException("Client not found"));
                return new User(client.getClientId(), client.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_CLIENT")));

            case "engineer":
                Engineer engineer = engineerRepository.findByEngineerName(username) // Find by engineerName
                        .orElseThrow(() -> new UsernameNotFoundException("Engineer not found"));
                return new User(engineer.getEngineerName(), engineer.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ENGINEER")));

            default:
                throw new UsernameNotFoundException("Role not recognized.");
        }
    }
}
