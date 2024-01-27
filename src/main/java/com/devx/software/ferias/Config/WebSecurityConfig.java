package com.devx.software.ferias.Config;

import com.devx.software.ferias.Components.JwtAuthenticationEntryPoint;
import com.devx.software.ferias.Components.JwtRequestFilter;
import com.devx.software.ferias.Services.Auth.UsersDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersDetailsServiceImpl usersDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersDetailsServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // X-Frame-Options: SAMEORIGIN
        //httpSecurity.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));

        // X-Content-Type-Options: NOSNIFF
        httpSecurity.headers().defaultsDisabled().contentTypeOptions();

        // Cookies: JSESSIONID
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/logout").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/password-recovery").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/refresh-token").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/enterprise-registration").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/my-profile").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/test").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/session/{\\d+}/profile/{\\d+}").permitAll()

                .antMatchers("/shared/**").permitAll()

                .antMatchers(HttpMethod.POST, "/auth/operacion/create").permitAll()


                .antMatchers(HttpMethod.GET, "/catalogos/**").permitAll()


                .antMatchers(HttpMethod.GET, "/idiomas/**").permitAll()

                .antMatchers(HttpMethod.GET, "/files/getUrl").permitAll()
                .antMatchers(HttpMethod.GET, "/files/get-object-by-filename").permitAll()
                .antMatchers(HttpMethod.POST, "/files/getManyUrl").permitAll()
                .antMatchers(HttpMethod.POST, "/files/uploadObjectWithOriginalName").permitAll()

                .antMatchers(HttpMethod.GET, "/capacitaciones/findUsuarioCapacitacionByUuid/**").permitAll()

                .anyRequest().permitAll();

        httpSecurity.addFilterAfter(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
