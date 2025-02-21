package com.IT.SpringBootAngular.config;

// Importation des classes nécessaires
import com.IT.SpringBootAngular.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe de configuration de la sécurité Spring Security
 */
@Configuration // Indique que cette classe est une classe de configuration Spring
@EnableWebSecurity // Active la configuration de la sécurité Web
@EnableMethodSecurity // Active la sécurité basée sur les annotations au niveau des méthodes
public class WebSecurityConfiguration {

    private final JwtRequestFilter jwtRequestFilter;

    /**
     * Injection du filtre JWT via le constructeur
     */
    @Autowired
    public WebSecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Configuration de la chaîne de filtres de sécurité
     * @param security Objet HttpSecurity pour la configuration de la sécurité
     * @return SecurityFilterChain configurée
     * @throws Exception en cas d'erreur de configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security
                .csrf(csrf -> csrf.disable()) // Désactive la protection CSRF (utile pour les API REST)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/login").permitAll() // Autorise l'accès sans authentification
                        .requestMatchers("/singup").permitAll() // (Redondant avec "/signup")
                        .requestMatchers("/admin/**", "/employee/**").permitAll() // Autorise ces routes (devrait être restreint)
                        .anyRequest().permitAll() // Autorise toutes les autres requêtes (devrait être sécurisé)
                )
                .sessionManagement(sess ->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Désactive la gestion de session (JWT utilisé)
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // Ajoute le filtre JWT avant le filtre d'authentification standard
                .build();
    }

    /**
     * Bean pour l'encodage des mots de passe avec BCrypt
     * @return Instance de BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean pour la gestion de l'authentification
     * @param configuration Configuration de l'authentification
     * @return AuthenticationManager configuré
     * @throws Exception en cas d'erreur de configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
