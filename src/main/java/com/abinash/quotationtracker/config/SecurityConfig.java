package com.abinash.quotationtracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Tells Spring that this class contains bean definitions (configuration settings)
@EnableWebSecurity // Enables Spring Security’s web security support in the project
public class SecurityConfig {

    @Bean // Tells Spring to manage the return value of this method as a 'Bean' in the application context
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF (Cross-Site Request Forgery) protection
                .csrf(csrf -> csrf
                        // Disable CSRF for the H2 console because it uses its own security tokens
                        // and would otherwise block you from logging in.
                        .ignoringRequestMatchers("/h2-console/**")
                )

                // 2. Authorization Rules
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to access the H2 console path without logging in
                        .requestMatchers("/h2-console/**").permitAll()
                        // For now, allow all other requests to pass through without authentication
                        .anyRequest().permitAll()
                )

                // 3. Header Security
                .headers(headers -> headers
                        // H2 console uses <frameset> or <iframe> tags.
                        // Spring Security blocks frames by default to prevent "clickjacking."
                        // We disable it here specifically so the H2 console can render in the browser.
                        .frameOptions(frame -> frame.disable())
                )

                // 4. Login Mechanisms
                // Disables the default Spring Security login page
                .formLogin(form -> form.disable())
                // Disables 'Basic' popup authentication (the browser's default login prompt)
                .httpBasic(basic -> basic.disable());

        // Build and return the configured security chain object
        return http.build();
    }
}