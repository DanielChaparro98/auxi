package com.microservice.login.config

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig {

    @Bean
    fun securityFilterChain(http:HttpSecurity):SecurityFilterChain{
        return http
            .csrf { csrf ->
                csrf
                    .disable()
            }
            .authorizeHttpRequests{ authRequest ->
                authRequest
                    .anyRequest().permitAll()
                }
            .formLogin(Customizer.withDefaults())
            .build();
    }
}