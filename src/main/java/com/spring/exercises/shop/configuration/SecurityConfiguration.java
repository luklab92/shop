package com.spring.exercises.shop.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/")
                .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/index")
                .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                /*.antMatchers("/index.html/**")
                .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")*/
                .antMatchers("/adduser")
                .hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
                .and()
                    .csrf().disable()
                    .headers().frameOptions().disable()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                    .loginProcessingUrl("/login-process")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/index")
                .and()
                    .logout()
                    .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        User.UserBuilder users = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(users.username("admin@admin.pl")
                        .password("password")
                        .roles("ADMIN")
                        .build())
                .withUser(users.username("user@user.pl")
                        .password("password")
                        .roles("USER")
                        .build());
    }
}
