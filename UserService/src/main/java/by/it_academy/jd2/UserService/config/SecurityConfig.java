package by.it_academy.jd2.UserService.config;

import by.it_academy.jd2.UserService.controllers.filter.JwtFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        // Set permissions on endpoints
        http.authorizeRequests()
           //     .antMatchers("/api/v1/users/{uuid}/**").hasRole("ADMIN")
                .antMatchers("/api/v1/users").hasRole("ADMIN")
                .antMatchers("/api/v1/users/login").permitAll()
                .antMatchers("/api/v1/users/registration").permitAll()
                .antMatchers("/api/v1/users/me").authenticated()
                // Our private endpoints
                .anyRequest().authenticated();

        // Add JWT token filter
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
    }
}