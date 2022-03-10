package com.github.pedroluiznogueira.blog.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() // refeering the authorization
                .antMatchers(HttpMethod.GET, "/api/**") // all get requests, with this url
                .permitAll()// will be permited
                .anyRequest() // refeering to any other request
                .authenticated() // refeering that they will have to be authenticated
                .and()
                .httpBasic(); // all end-points with basic auth
    }

    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails pedro = User.builder().username("pedro").password("password").roles("USER").build();
        UserDetails admin = User.builder().username("admin").password("password").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(pedro, admin);
    }
}
