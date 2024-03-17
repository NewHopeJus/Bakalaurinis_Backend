package com.example.bakalaurinis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final MyUserDetailsService myUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfiguration(MyUserDetailsService myUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    //Stateless reiskia kad kiekvienas requestas priimamas atskirai (independently) ir mnera sesiju
    //turi siusti kiekviena karta ir tokena kartu useris
    //bean yra objektas kuri springas managina
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Isjungia crrf protection, nes su restais nebutina sito
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                        .anyRequest().authenticated()) // Visi kiti apart pries tai definintu tik prisijungusiems
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); // buildina  SecurityFilterChain instanca
    }

    //sitas metodas konfiguruoja koki userDetails service naudoti ir koki encoderi ir galiausiai grazina authentication manageri
    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
}
