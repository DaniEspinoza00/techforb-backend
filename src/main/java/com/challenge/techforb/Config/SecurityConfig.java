package com.challenge.techforb.Config;

import com.challenge.techforb.GlobalExceptionHandler.JwtEntryPoint.JwtAuthenticationEntryPoint;
import com.challenge.techforb.User.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authRequest->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                .requestMatchers(HttpMethod.GET,"/techforb/user/{id}").authenticated()

                                .requestMatchers(HttpMethod.GET,"/techforb/sensors").authenticated()
                                .requestMatchers(HttpMethod.GET,"/techforb/sensors/all").authenticated()
                                .requestMatchers(HttpMethod.GET,"/techforb/sensors/{id}").authenticated()
                                .requestMatchers(HttpMethod.POST,"/techforb/sensors").authenticated()
                                .requestMatchers(HttpMethod.PUT,"/techforb/sensors/{id}").authenticated()
                                .requestMatchers(HttpMethod.DELETE,"/techforb/sensors/{id}").authenticated()

                                .requestMatchers(HttpMethod.GET,"/techforb/plant").authenticated()
                                .requestMatchers(HttpMethod.GET,"/techforb/plant/{id}").authenticated()
                                .requestMatchers(HttpMethod.POST,"/techforb/plant").authenticated()
                                .requestMatchers(HttpMethod.PUT,"/techforb/plant/{id}").authenticated()
                                .requestMatchers(HttpMethod.PUT,"/techforb/plant/{id}/sensor").authenticated()
                                .requestMatchers(HttpMethod.DELETE,"/techforb/plant/{id}").authenticated()

                                .anyRequest().authenticated()
                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
