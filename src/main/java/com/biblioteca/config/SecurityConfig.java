package com.biblioteca.config;

import com.biblioteca.security.JwtFilter;
import com.biblioteca.security.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
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
                                auth.requestMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**").permitAll()
                                        .requestMatchers(HttpMethod.GET,"/api/books","/api/books/{id}","/api/authors","/api/categories","/api/users/{id}","/api/users/{id}/loans").permitAll()
                                        .requestMatchers(HttpMethod.POST,"/api/auth/login","/api/users","/api/loans").permitAll()
                                        .requestMatchers(HttpMethod.PUT,"/api/users/{id}").permitAll()
                                        .requestMatchers(HttpMethod.PATCH,"/api/loans/{id}/return").permitAll()
                                        .requestMatchers(HttpMethod.GET,"/api/loans/overdue").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET,"/api/loans/overdue").hasRole("ATTENDANT")
                                        .requestMatchers(HttpMethod.POST,"/api/categories","/api/authors","/api/books").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE,"/api/books/{id}").hasRole("ADMIN")
                                        .anyRequest().authenticated()

                                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler())

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint(){
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            response.getWriter().write("""
                    {
                    "status":401,
                    "message":"Você precisa estar autenticado."
                    }
                    """);
        };
    }
    @Bean
    AccessDeniedHandler accessDeniedHandler(){
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");

            response.getWriter().write("""
                    {
                    "status":403,
                    "message":"Você não possui permissão para acessar este recurso."
                    }
                    """);
        };
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
