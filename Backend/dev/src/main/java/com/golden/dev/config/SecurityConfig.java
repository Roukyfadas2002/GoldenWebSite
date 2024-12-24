package com.golden.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Désactiver CSRF
            .csrf(csrf -> csrf.disable())
            // Gestion des CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // Autoriser toutes les requêtes
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            // Permettre les frames pour H2 Console
            .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200"); // Autorise l'origine Angular
        configuration.addAllowedMethod("*"); // Autorise toutes les méthodes HTTP (GET, POST, etc.)
        configuration.addAllowedHeader("*"); // Autorise tous les en-têtes
        configuration.setAllowCredentials(true); // Autorise les cookies et sessions

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Applique la configuration CORS globalement
        return source;
    }
}
