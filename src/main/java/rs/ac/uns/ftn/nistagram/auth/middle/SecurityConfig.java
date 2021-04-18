package rs.ac.uns.ftn.nistagram.auth.middle;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rs.ac.uns.ftn.nistagram.auth.identity.IdentityService;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final IdentityService identityService;
    private final AuthFilter authFilter;

    public SecurityConfig(IdentityService identityService, AuthFilter authFilter) {
        this.identityService = identityService;
        this.authFilter = authFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(identityService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().anyRequest().authenticated().and()
            .cors().and().csrf().disable()

            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .exceptionHandling()
            .authenticationEntryPoint((req, res, err) ->
                    res.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        err.getMessage()
                    )
            )
            .and()

            .addFilterAfter(authFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
