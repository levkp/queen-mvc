package queenapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

          // @formatter:off
          http
                  .httpBasic()
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.OK))
                  .and()
                  .authorizeRequests()
                        .antMatchers("/", "/register", "/login", "/api/**/*")
                            .permitAll()
                        .regexMatchers(HttpMethod.GET, ".+\\.(css|js|map|woff2?|jpg|png|ico)(\\?.*)?")
                            .permitAll()
                        .anyRequest()
                            .authenticated()
                  .and()
                  .formLogin()
                        .loginPage("/login")
                            .permitAll()
                  .and()
                  .logout()
                        .permitAll()
                  .and()
                  .csrf().disable();
          // @formatter:on
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
