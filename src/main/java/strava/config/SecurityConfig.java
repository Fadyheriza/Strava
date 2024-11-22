package strava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll() // Permit H2 and Swagger UI
                        .requestMatchers("/", "/login", "/oauth2/**").permitAll() // Permit login and OAuth endpoints
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // Disable CSRF for H2 console
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Allow H2 console frames
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Specify the custom login page
                        .defaultSuccessUrl("/dashboard", true) // Redirect to dashboard on successful login
                        .failureUrl("/login?error") // Redirect to login page on failure
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                );

        return http.build();
    }
}
