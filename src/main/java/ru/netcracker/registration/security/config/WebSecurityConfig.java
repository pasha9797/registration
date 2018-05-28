package ru.netcracker.registration.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.netcracker.registration.security.filter.AuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(super.authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()

                //ADMIN REQUESTS
                .antMatchers(
                        "/userapi/get/all/**"
                ).hasAuthority("Admin")

                //AUTHENTICATED REQUESTS
                .antMatchers(
                        "/userapi/get/**",
                        "/userapi/delete/**",
                        "/userapi/edit/**",
                        "/userPage**",
                        "/userquests**",
                        "/upload-photo**",
                        "/upload-info**",
                        "/get-quests-by-owner**",
                        "/get-progresses-by-user**",
                        "/get-progress-for-quest**",
                        "/report-quest",
                        "/cancel-quest-reports"
                ).authenticated()

                //BUSINESS REQUESTS
                .antMatchers(
                        "/newquest**",
                        "/newoffer**",
                        "/confirmations**",
                        "/get-all-confirmations**",
                        "/confirmation-request**",
                        "/get-owned-offers**",
                        "/save-offer**"
                ).hasAnyAuthority("Business", "Admin")

                //NORMAL USER REQUESTS
                .antMatchers(
                        "/join-quest**",
                        "/get-my-offers**",
                        "/purchase-offer**"
                ).hasAnyAuthority("Default", "Admin")
                .anyRequest().permitAll();

        // Custom JWT based authentication
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}