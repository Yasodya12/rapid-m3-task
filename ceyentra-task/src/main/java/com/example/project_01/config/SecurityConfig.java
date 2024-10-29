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
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    public SecurityConfig(UserService adminService, JwtAuthorizationFilter jwtAuthorizationFilter,CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.adminService = adminService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityFilterChain");
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(request -> {

                    request.requestMatchers("/log").permitAll().
                            requestMatchers("user").permitAll().
                            requestMatchers("/loan").hasAuthority("ADMIN").
                            requestMatchers("/pay").hasAuthority("ADMIN").
                            requestMatchers("/account").hasAuthority("ADMIN").
                            requestMatchers("/transaction").hasAuthority("ADMIN").
                            requestMatchers("/tasks").hasAuthority("ADMIN").

                            requestMatchers("/withdraw").hasAuthority("USER")


                            .anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}