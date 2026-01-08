package be.vdab.onderhoud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    private static final String INGENIEUR = "ingenieur";
    private static final String TECHNIEKER = "technieker";
    @Bean
    InMemoryUserDetailsManager maakPrincipals() {
        var ing = User.withUsername("ing")
                .password("{noop}ing")
                .authorities(INGENIEUR)
                .build();
        var tech =  User.withUsername("tech")
                .password("{noop}tech")
                .authorities(TECHNIEKER)
                .build();
        return new InMemoryUserDetailsManager(ing, tech);
    }

    @Bean
    SecurityFilterChain geefRechten(HttpSecurity http) throws Exception {
        http.formLogin(withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/login","/images/**", "/css/**", "/js/**", "/", "/accessDenied.html").permitAll()
                .requestMatchers(HttpMethod.PUT,"/taken/bevestigen/**").permitAll()
                .requestMatchers("/check.html").hasAuthority(INGENIEUR)
                .requestMatchers("/index.html").hasAnyAuthority(TECHNIEKER, INGENIEUR)
                .anyRequest().authenticated()
        );
        http.exceptionHandling(
                handling -> handling.accessDeniedPage("/accessDenied.html"));
        return http.build();
    }
}
