package com.LT1Init.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.LT1Init.backend.security.JwtFilter;
import com.LT1Init.backend.service.CustomUserService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserService customUserService;
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf ->csrf.disable())      // CSRF korumasını kapat
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                 .requestMatchers("/api/auth/**").permitAll()
                 .requestMatchers(HttpMethod.GET, "/api/product/**").hasAnyRole("ADMIN","USER")
                 .requestMatchers(HttpMethod.POST,"/api/product/**").hasAnyRole("ADMIN")
                 .requestMatchers(HttpMethod.PUT, "/api/product/**").hasAnyRole("ADMIN")
                 .requestMatchers(HttpMethod.DELETE,"/api/product/**").hasAnyRole("ADMIN")
                 .requestMatchers(HttpMethod.GET, "/api/category/**").hasAnyRole("ADMIN","USER")
                 .requestMatchers(HttpMethod.POST,"/api/category/**").hasAnyRole("ADMIN")
                 .requestMatchers(HttpMethod.PUT, "/api/category/**").hasAnyRole("ADMIN")
                 .requestMatchers(HttpMethod.DELETE,"/api/category/**").hasAnyRole("ADMIN")
                 .requestMatchers(HttpMethod.GET, "/api/color/**").hasAnyRole("ADMIN","USER")
                 .requestMatchers(HttpMethod.POST,"/api/color/**").hasAnyRole("ADMIN")
                 .requestMatchers(HttpMethod.PUT, "/api/color/**").hasAnyRole("ADMIN")
                 .requestMatchers(HttpMethod.DELETE,"/api/color/**").hasAnyRole("ADMIN")
                 .anyRequest().authenticated()
            )
            .userDetailsService(customUserService)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http
        .getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(customUserService)
        .passwordEncoder(passwordEncoder())
        .and()
        .build();
    }
}
