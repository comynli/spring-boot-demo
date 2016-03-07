package me.ele.opdev.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by xuemingli on 16/3/7.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationProvider provider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.addFilterBefore(new JWTAuthenticationFilter(), BasicAuthenticationFilter.class);
        http.authenticationProvider(provider);
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
    }
}
