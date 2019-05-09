package com.yarpg.application.configuration.rest;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;

//@Configuration
//@EnableWebSecurity
//extends WebSecurityConfigurerAdapter
public class WebSecurityConfig {
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.authorizeRequests()
    // .antMatchers("/", "/home")
    // .permitAll()
    // .anyRequest()
    // .authenticated()
    // .and()
    // .formLogin()
    // .loginPage("/login")
    // .permitAll()
    // .and()
    // .logout()
    // .permitAll();
    // }
    //
    // @Bean
    // @Override
    // public UserDetailsService userDetailsService() {
    // UserDetails user = User.withDefaultPasswordEncoder()
    // .username("user")
    // .password("password")
    // .roles("USER")
    // .build();
    //
    // new JdbcUserDetailsManager().
    //
    // return new InMemoryUserDetailsManager(user);
    // }
}
