package com.artemsolovev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/school").hasAnyRole("ADMIN")
                .antMatchers("/school/**").permitAll()
                .antMatchers(HttpMethod.POST, "/dish/**").hasAnyRole("WORKER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/dish/**").permitAll()
                .antMatchers("/dish").hasAnyRole("WORKER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/order").hasAnyRole("PARENT")
                .antMatchers(HttpMethod.POST, "/order/**").hasAnyRole("PARENT")
                .antMatchers("/order").permitAll()
                .antMatchers("/order/**").permitAll()
                .antMatchers(HttpMethod.POST, "/student").permitAll()
                .antMatchers("/student").hasAnyRole("STUDENT", "TEACHER")
                .antMatchers("/student/**").permitAll()
                .antMatchers(HttpMethod.POST, "/teacher").permitAll()
                .antMatchers("/teacher").hasAnyRole("TEACHER")
                .antMatchers("/teacher/**").hasAnyRole("TEACHER")
                .antMatchers(HttpMethod.POST, "/worker").permitAll()
                .antMatchers("/worker").hasAnyRole("WORKER")
                .antMatchers("/worker/**").hasAnyRole("WORKER")
                .antMatchers(HttpMethod.POST, "/parent").permitAll()
                .antMatchers("/parent").hasAnyRole("PARENT")
                .antMatchers("/parent/**").hasAnyRole("PARENT")
                .antMatchers("/user").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
                //.formLogin();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
