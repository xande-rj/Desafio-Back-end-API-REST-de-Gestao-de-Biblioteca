package com.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth->
                                auth.requestMatchers(HttpMethod.GET,"/api/books","/api/books/{id}","/api/authors","/api/categories","/api/users/{id}","/api/users/{id}/loans").permitAll()
                                        .requestMatchers(HttpMethod.POST,"/api/auth/login","/api/users","/api/loans").permitAll()
                                        .requestMatchers(HttpMethod.PUT,"/api/users/{id}").permitAll()
                                        .requestMatchers(HttpMethod.PATCH,"/api/loans/{id}/return").permitAll()

                                ).build();

    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
