package com.shopping.cart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * This class provides the security configuration for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.security.username}")
    private String username;

    @Value("${app.security.password}")
    private String password;

    /**
     * Configures the security filter chain for the HTTP requests.
     *
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an exception occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/products/**").authenticated() // Authentifizierung für alle /api/products/ Endpunkte
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults()); // Verwendung von Basic Auth

        return http.build();
    }

    /**
     * Returns the UserDetailsService object used for authentication and authorization.
     *
     * @return The UserDetailsService object.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}