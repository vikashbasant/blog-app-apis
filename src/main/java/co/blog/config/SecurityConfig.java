package co.blog.config;

import co.blog.constants.SecurityConfigConstants;
import co.blog.security.CustomUserDetailService;
import co.blog.security.JwtAuthenticationEntryPoint;
import co.blog.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig {


    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("===: SecurityConfig:: Inside securityFilterChain Method :===");
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(SecurityConfigConstants.PUBLIC_URL).permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.authenticationProvider(daoAuthenticationProvider());

        DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
        return defaultSecurityFilterChain;
    }

    /*
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        log.info("===: SecurityConfig:: Inside configure Method :===");
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(SecurityConfigConstants.PUBLIC_URL).permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
    */

    /*@Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        log.info("===: SecurityConfig:: Inside configure Method :===");
        auth.userDetailsService(this.customUserDetailService).passwordEncoder(getPasswordEncoder());
    }*/



    @Bean
    public PasswordEncoder getPasswordEncoder() {
        log.info("===: SecurityConfig:: Inside getPasswordEncoder Method:===");
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws Exception {
        log.info("===: SecurityConfig:: Inside authenticationManagerBean Method :===");
        return super.authenticationManagerBean();
    }*/

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        log.info("===: SecurityConfig:: Inside daoAuthenticationProvider Method:===");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(getPasswordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManagerBean (AuthenticationConfiguration configuration) throws Exception {
        log.info("===: SecurityConfig:: Inside authenticationManagerBean Method :===");
        return configuration.getAuthenticationManager();
    }
}
