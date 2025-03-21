package com.trimblecars.lease.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails car = User.withUsername("raj")
                .password(encoder.encode( "raj@123"))
                .roles("CAR_OWNER")
                .build();
        UserDetails user = User.withUsername("john")
                .password(encoder.encode( "john@123"))
                .roles("END_USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode( "admin@123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin,user,car);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/cars/**").hasAnyRole("CAR_OWNER","ADMIN")
                        .requestMatchers("/leases/**").hasAnyRole("END_USER","ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();

    }

}