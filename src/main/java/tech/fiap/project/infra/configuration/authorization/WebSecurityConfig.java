package tech.fiap.project.infra.configuration.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@SuppressWarnings("all")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private final KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter;

	public WebSecurityConfig(KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter) {
		this.keycloakJwtAuthenticationConverter = keycloakJwtAuthenticationConverter;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers(HttpMethod.POST, "/api/v1/kitchen").permitAll()
						.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**",
								"/v3/api-docs/**", "/webjars/**")
						.permitAll().anyRequest().authenticated())
				.csrf(AbstractHttpConfigurer::disable).oauth2Client(withDefaults()).oauth2ResourceServer(
						httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
								.jwt(jwtConfigurer -> jwtConfigurer
										.jwtAuthenticationConverter(keycloakJwtAuthenticationConverter)));

		return http.build();
	}

}