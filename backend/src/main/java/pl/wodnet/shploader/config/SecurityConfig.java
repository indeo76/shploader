package pl.wodnet.shploader.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${SECURITY_DISABLED:false}")
    private boolean securityDisabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (securityDisabled) {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().permitAll();
        } else {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic();
        }
    }
}



