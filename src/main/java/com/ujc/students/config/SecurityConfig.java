package com.ujc.students.config;

import com.ujc.students.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // Login e registo públicos
                .requestMatchers("/auth/login", "/auth/registar").permitAll()

                // ADMIN — acesso total
                .requestMatchers("/**").hasRole("ADMIN")

                // AGRICULTOR — gerir produtos e pedidos
                .requestMatchers(HttpMethod.GET,    "/produto/**").hasAnyRole("AGRICULTOR", "COMPRADOR")
                .requestMatchers(HttpMethod.POST,   "/produto/**").hasRole("AGRICULTOR")
                .requestMatchers(HttpMethod.PUT,    "/produto/**").hasRole("AGRICULTOR")
                .requestMatchers(HttpMethod.DELETE, "/produto/**").hasRole("AGRICULTOR")
                .requestMatchers(HttpMethod.GET,    "/pedido/**").hasAnyRole("AGRICULTOR", "COMPRADOR")
                .requestMatchers(HttpMethod.PUT,    "/pedido/**").hasRole("AGRICULTOR")
                .requestMatchers(HttpMethod.GET,    "/entrega/**").hasAnyRole("AGRICULTOR", "COMPRADOR")
                .requestMatchers(HttpMethod.PUT,    "/entrega/**").hasRole("AGRICULTOR")

                // COMPRADOR — efectuar compras
                .requestMatchers(HttpMethod.POST,   "/pedido/**").hasRole("COMPRADOR")
                .requestMatchers(HttpMethod.POST,   "/entrega/**").hasRole("COMPRADOR")

                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
