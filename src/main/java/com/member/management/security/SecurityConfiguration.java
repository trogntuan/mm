package com.member.management.security;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/authentication")
                .usernameParameter("id")
                .passwordParameter("password")
                .failureUrl("/login?error=true")
                .successHandler(((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedInUser", authentication.getPrincipal());
                    response.sendRedirect("/home");
                }))
                .permitAll()
        ).logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        ).authorizeHttpRequests(request -> request
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/home").authenticated()
                .requestMatchers("/register/**").permitAll()
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }
}
