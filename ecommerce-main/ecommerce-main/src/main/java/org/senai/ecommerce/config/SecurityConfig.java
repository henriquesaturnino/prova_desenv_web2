package org.senai.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf().disable().authorizeHttpRequests(
                authorizeConfig -> {
                    authorizeConfig.requestMatchers("/usuario/add").permitAll();
                    authorizeConfig.requestMatchers("/produto/lista").permitAll();
                    authorizeConfig.requestMatchers("/produto/{id}").permitAll();
                    authorizeConfig.requestMatchers("/produto/add").hasRole("ADMIN");
                    authorizeConfig.requestMatchers(HttpMethod.PUT,"/produto/{id}").hasRole("ADMIN");
                    authorizeConfig.requestMatchers(HttpMethod.DELETE,"/produto/{id}").hasRole("ADMIN");

                    authorizeConfig.anyRequest().authenticated();
                }
        ).httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}