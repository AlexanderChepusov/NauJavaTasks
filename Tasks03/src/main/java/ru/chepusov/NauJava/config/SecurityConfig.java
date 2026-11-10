package ru.chepusov.NauJava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import ru.chepusov.NauJava.service.UserAuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserAuthService userAuthService;

    public SecurityConfig(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((reqests) -> reqests
                    .requestMatchers("/swagger-ui/index.html", "/delete").hasRole("ADMIN")
                    .requestMatchers("/create").hasRole("CREATOR")
                    .requestMatchers("/login", "/register").permitAll()
                    .requestMatchers("/", "/users").permitAll()
                    .requestMatchers("/**").authenticated()
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .permitAll()
                )
        ;
        return http.build();
    }

//    .formLogin(form -> form
//            .loginPage("/login") // Страница входа
//            .defaultSuccessUrl("/profile", true) // <-- КУДА ИДТИ ПОСЛЕ ВХОДА
//            .permitAll()
//        )
//                .logout(logout -> logout
//            .logoutUrl("/logout")
//            .logoutSuccessUrl("/login?logout") // <-- КУДА ИДТИ ПОСЛЕ ВЫХОДА
//            .permitAll()
//        );


//    public void addViewControllers(ViewControllerRegistry registry){
//        //registry.addViewController("/").setViewName("home");
//        registry.addViewController("/login");
//    }
}
