package com.golden.dev.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implémentation simplifiée : remplacer par une recherche en base de données
        if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password("{noop}admin") // Utilisation temporaire de `{noop}` pour éviter le hashage
                    .roles("ADMIN")
                    .build();
        } else if ("user".equals(username)) {
            return User.builder()
                    .username("user")
                    .password("{noop}user") // Utilisation temporaire de `{noop}` pour éviter le hashage
                    .roles("USER")
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }
}
