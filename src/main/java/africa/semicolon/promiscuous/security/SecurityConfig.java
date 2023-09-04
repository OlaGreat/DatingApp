package africa.semicolon.promiscuous.security;

import africa.semicolon.promiscuous.security.filters.PromiscuousAuthenticationFilter;
import africa.semicolon.promiscuous.security.filters.PromiscuousAuthorizationFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static africa.semicolon.promiscuous.models.Role.CUSTOMER;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@AllArgsConstructor
@Slf4j
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;



   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        final String[] publicEndPoints = {"/api/v1/user", "/login"};
//        log.info("I AM HERE OHHGHHHHHHHHHHH");
        return httpSecurity
                .addFilterAt(new PromiscuousAuthenticationFilter(authenticationManager),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new PromiscuousAuthorizationFilter(), PromiscuousAuthenticationFilter.class)
                .sessionManagement(customizer->customizer.sessionCreationPolicy(STATELESS))
                .csrf(c->c.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(c->c.requestMatchers(POST, publicEndPoints).permitAll())
                .authorizeHttpRequests(c->c.anyRequest().hasAuthority(CUSTOMER.name()))
                .build();

//        return httpSecurity.sessionManagement(customizer-> customizer.sessionCreationPolicy(STATELESS))
//                .addFilterAt(new PromiscuousAuthenticationFilter(authenticationManager),
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new PromiscuousAuthorizationFilter(), PromiscuousAuthenticationFilter.class)
//                .authorizeHttpRequests(c->c.anyRequest().permitAll().anyRequest())
//                .build();

//        return httpSecurity
//                .cors(customizer -> customizer.configure(httpSecurity)).csrf(customizer -> customizer.configure(httpSecurity))
//                .sessionManagement(customizer -> customizer.sessionCreationPolicy(STATELESS))
//                .addFilterAt(new PromiscuousAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests((customizer)->customizer.requestMatchers(POST,"/api/v1/user").permitAll())
//                .authorizeHttpRequests((customizer)->customizer.requestMatchers(POST,"/api/v1/user/uploadMedia")
//                        .hasRole(CUSTOMER.name()))
//                .authorizeHttpRequests((customizer)->customizer.requestMatchers(PUT,"/api/v1/user/**")
//                        .hasRole(CUSTOMER.name()))
//                .authorizeHttpRequests(customizer->customizer.anyRequest().authenticated())
//
//
//                .build();
    }
}
