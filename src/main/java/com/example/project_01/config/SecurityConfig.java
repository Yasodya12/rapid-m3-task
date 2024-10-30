package com.example.project_01.config;


import com.example.project_01.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final UserService adminService;

    public SecurityConfig(UserService adminService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.adminService = adminService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityFilterChain");
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(request -> {

                    request.requestMatchers("/log").permitAll().
                            requestMatchers("/tasks").permitAll().
                            requestMatchers("/tasks/**").permitAll().
                            requestMatchers("/loan").hasAuthority("ADMIN").

                            requestMatchers("/pay").hasAuthority("ADMIN").
                            requestMatchers("/account").hasAuthority("ADMIN").
                            requestMatchers("/transaction").hasAuthority("ADMIN").
                            requestMatchers("/user").hasAuthority("ADMIN").
                            requestMatchers("/withdraw").hasAuthority("USER")


                            .anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}