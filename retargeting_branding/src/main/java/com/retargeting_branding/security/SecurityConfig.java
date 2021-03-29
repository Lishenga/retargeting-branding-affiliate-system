package com.retargeting_branding.security;

import com.retargeting_branding.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
    throws Exception {
    auth.userDetailsService(authenticationService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
      .ignoring()
      .antMatchers(
        "/swagger-ui.html",
        "/webjars/**",
        "/registeruser",
        "/heathcheck",
        "/swagger-ui/**",
        "/swagger-ui-custom.html",
        "/api-docs",
        "/swagger-resources/**",
        "/swagger-resources",
        "/v2/api-docs", "/configuration/**", "/swagger*/**" 
      );
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .csrf()
      .disable()
      .authorizeRequests()
      .antMatchers(
        "/swagger-ui.html",
        "/webjars/**",
        "/registeruser",
        "/heathcheck",
        "/swagger-ui/**",
        "/swagger-ui-custom.html",
        "/api-docs",
        "/swagger-resources/**",
        "/swagger-resources",
        "/v2/api-docs", "/configuration/**", "/swagger*/**" 
      )
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .exceptionHandling()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.addFilterBefore(
      jwtRequestFilter,
      UsernamePasswordAuthenticationFilter.class
    );
  }
}