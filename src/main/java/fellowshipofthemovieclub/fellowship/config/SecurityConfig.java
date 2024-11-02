package fellowshipofthemovieclub.fellowship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disables CSRF; adjust based on your security requirements
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/**", "/currentmovie/**","/text/textbyname", "/tautulli/playcount").permitAll() // Permit access to signup and login
                        .requestMatchers("/text/get","/text/updatetext").hasRole("ADMIN")  // Restrict access to ADMIN role
                        .anyRequest().authenticated() // Require authentication for all other endpoints
                )
                .formLogin(form -> form
                        .loginPage("/signup")                  // Custom login page
                        .defaultSuccessUrl("/index")           // Redirect after login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")                 // Default logout URL
                        .logoutSuccessUrl("/logout")    // Redirect after logout
                        .permitAll()
                );

        return http.build();
    }
}

