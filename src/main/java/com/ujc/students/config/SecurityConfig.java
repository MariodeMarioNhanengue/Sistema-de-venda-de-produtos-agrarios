package com.ujc.students.config;

import com.ujc.students.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

                // ── Público ───────────────────────────────────────────────────
                .requestMatchers("/auth/login", "/auth/registar").permitAll()

                // ── Swagger / OpenAPI ─────────────────────────────────────────
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/api-docs",
                    "/api-docs/**",
                    "/v3/api-docs",
                    "/v3/api-docs/**"
                ).permitAll()

                // ── ADMIN — gere compradores e agricultores ───────────────────
                .requestMatchers("/comprador/**").hasAnyRole("ADMIN", "COMPRADOR")
                .requestMatchers("/agricultor/**").hasRole("ADMIN")

                // ── AGRICULTOR — gere produtos e pedidos ─────────────────────
                .requestMatchers(HttpMethod.GET,    "/produto/**").hasAnyRole("ADMIN", "AGRICULTOR", "COMPRADOR")
                .requestMatchers(HttpMethod.POST,   "/produto/**").hasAnyRole("ADMIN", "AGRICULTOR")
                .requestMatchers(HttpMethod.PUT,    "/produto/**").hasAnyRole("ADMIN", "AGRICULTOR")
                .requestMatchers(HttpMethod.DELETE, "/produto/**").hasAnyRole("ADMIN", "AGRICULTOR")

                // ── COMPRADOR — pesquisa e compra ─────────────────────────────
                .requestMatchers(HttpMethod.GET,    "/pedido/**").hasAnyRole("ADMIN", "AGRICULTOR", "COMPRADOR")
                .requestMatchers(HttpMethod.POST,   "/pedido/**").hasAnyRole("ADMIN", "COMPRADOR")
                .requestMatchers(HttpMethod.PUT,    "/pedido/**").hasAnyRole("ADMIN", "AGRICULTOR")

                .requestMatchers(HttpMethod.GET,    "/entrega/**").hasAnyRole("ADMIN", "AGRICULTOR", "COMPRADOR")
                .requestMatchers(HttpMethod.POST,   "/entrega/**").hasAnyRole("ADMIN", "COMPRADOR")
                .requestMatchers(HttpMethod.PUT,    "/entrega/**").hasAnyRole("ADMIN", "AGRICULTOR")

                // ── Qualquer outro endpoint exige autenticação ────────────────
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Suprime o "Using generated security password" — JWT trata da autenticação
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
