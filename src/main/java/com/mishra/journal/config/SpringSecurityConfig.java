package com.mishra.journal.config;

import com.mishra.journal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public SpringSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth-> {
            auth.requestMatchers("/public**").permitAll()
                    .requestMatchers("/journal/**","/user/**").authenticated()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll();}).formLogin(form -> {})
                .httpBasic(basic -> basic.realmName("MyApp_mishra"))
                .userDetailsService(userDetailsService);
        return http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
