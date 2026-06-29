package com.biblioteca.config;

import com.biblioteca.security.JwtFilter;
import com.biblioteca.security.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private  final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth->
                                auth.requestMatchers(HttpMethod.GET,"/api/books","/api/books/{id}","/api/authors","/api/categories","/api/users/{id}","/api/users/{id}/loans").permitAll()
                                        .requestMatchers(HttpMethod.POST,"/api/auth/login","/api/users","/api/loans").permitAll()
                                        .requestMatchers(HttpMethod.PUT,"/api/users/{id}").permitAll()
                                        .requestMatchers(HttpMethod.PATCH,"/api/loans/{id}/return").permitAll()
                                        .requestMatchers(HttpMethod.GET,"/api/loans/overdue").hasRole("ADMIN")
                                        .anyRequest().authenticated()

                                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
