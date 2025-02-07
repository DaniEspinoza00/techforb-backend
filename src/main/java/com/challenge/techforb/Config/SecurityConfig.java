package com.challenge.techforb.Config;

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf->csrf.disable())//para deshabilitar el token basado en csrf cross site request forgery (medida de seguridad como JWT para las solicitudes POST)
                .authorizeHttpRequests(authRequest->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/techforb/user/{id}").authenticated()

                                .requestMatchers(HttpMethod.GET,"/techforb/sensors").authenticated()
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
                .sessionManagement(sessionManager -> //para la autenticacion basada en JWT
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
