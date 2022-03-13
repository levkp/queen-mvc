package queenapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
          // @formatter:off
          http
                  .httpBasic()
                        .authenticationEntryPoint(httpStatusEntryPoint())
                  .and()
                  .authorizeRequests()
                        .antMatchers("/", "/register", "/albums", "/api/**/*")
                            .permitAll()
                        .regexMatchers(HttpMethod.GET, ".+\\.(css|js|map|woff2?|jpg)(\\?.*)?")
                            .permitAll()
                        .anyRequest()
                            .authenticated()
                        .and()
                  .formLogin()
                        .loginPage("/login")
                            .permitAll()
                        .and()
                  .logout()
                        .permitAll();
          // @formatter:on
    }

    private HttpStatusEntryPoint httpStatusEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
    }
}
