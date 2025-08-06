package Part1.config;

import org.springframework.context.annotation.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder encoder) {
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

            manager.createUser(User.withUsername("bob")
                    .password(encoder.encode("1234"))
                    .roles("EMPLOYEE_SALES")
                    .build());

            manager.createUser(User.withUsername("mary")
                    .password(encoder.encode("1234"))
                    .roles("EMPLOYEE_FINANCE")
                    .build());

            return manager;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/shop").permitAll()
                            .requestMatchers("/orders").hasAnyRole("EMPLOYEE_SALES", "EMPLOYEE_FINANCE")
                            .requestMatchers("/payments").hasRole("EMPLOYEE_FINANCE")
                            .anyRequest().authenticated()
                    )
                    .httpBasic(basic -> {});

            return http.build();
        }
    }



