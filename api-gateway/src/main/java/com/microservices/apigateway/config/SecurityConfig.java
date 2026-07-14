package com.microservices.apigateway.config;

import com.microservices.apigateway.security.JwtAuthenticationManager;
import com.microservices.apigateway.security.JwtServerSecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.WebFilter;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig{

    private final JwtAuthenticationManager jwtAuthenticationManager;
    private final JwtServerSecurityContextRepository securityContextRepository;

    public SecurityConfig(JwtAuthenticationManager jwtAuthenticationManager, JwtServerSecurityContextRepository securityContextRepository) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        AuthenticationWebFilter authenticationWebFilter=new AuthenticationWebFilter(jwtAuthenticationManager);
        authenticationWebFilter.setSecurityContextRepository(securityContextRepository);
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(exchange->exchange.pathMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .pathMatchers("/actuator/**").permitAll().pathMatchers("/auth/register").permitAll().anyExchange().authenticated()
                ).addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION).build();
    }
}