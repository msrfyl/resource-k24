package com.msrfyl.k24.resource.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

//    @Value("\${jwt-issuer}")
//    lateinit

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        JwtIssuerAuthenticationManagerResolver jwtIssuer = new JwtIssuerAuthenticationManagerResolver("http://127.0.0.1:7000");
        httpSecurity.authorizeHttpRequests( i -> {
            i.requestMatchers("/assets/**").permitAll()
                    .requestMatchers("/api/**").authenticated();
        }).oauth2ResourceServer( i -> {
            i.authenticationManagerResolver(jwtIssuer);
        }).sessionManagement( i -> {
            i.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        return httpSecurity.build();
    }

}
