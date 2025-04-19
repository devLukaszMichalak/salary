package dev.lukaszmichalak.salary.security;

import dev.lukaszmichalak.salary.security.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityFilterConfig {

  private final JwtTokenFilter jwtTokenFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    return http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .cors(
            cors -> {
              UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

              CorsConfiguration config = new CorsConfiguration();
              config.setAllowCredentials(true);
              config.addAllowedOrigin("http://localhost:4200");
              config.addAllowedOrigin("http://localhost:8080");
              config.addAllowedHeader("*");
              config.addAllowedMethod("*");
              source.registerCorsConfiguration("/**", config);

              cors.configurationSource(source);
            })
        .csrf(AbstractHttpConfigurer::disable)
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers(HttpMethod.POST, "/api/v1/auth/validate-token")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/auth/register", "/api/v1/auth/login")
                    .anonymous()
                    .requestMatchers(HttpMethod.GET, "/api/v1/auth/check-email")
                    .anonymous()
                    .anyRequest()
                    .authenticated())
        .build();
  }
}
