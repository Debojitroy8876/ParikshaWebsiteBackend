package com.exam.config;

import com.exam.service.imple.UserDetailsServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity()
@Configuration

public class SecurityConfig {

    @Autowired
    private ObjectPostProcessor<Object> objectPostProcessor;
        @Autowired
        private JwtAuthenticationEntryPoint unauthorizedHandler;
        @Autowired
        private UserDetailsServiceImple userDetailsServiceImple;
        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;
        @Bean
        public BCryptPasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
    /*@Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/



    @Primary
    @Bean
    public AuthenticationManagerBuilder myAuthenticationManagerBuilder() throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = new AuthenticationManagerBuilder(objectPostProcessor);

        // Configure authentication providers, password encoder, etc.
        authenticationManagerBuilder.userDetailsService(this.userDetailsServiceImple).passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder;
    }





    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable();
                 http.authorizeHttpRequests()
                .requestMatchers("/generate-token","/users/").permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                         .and()

                /*.antMatchers("/generate-token","/user/").permitAll()


                .authorizeHttpRequests(authorizeRequests ->
                .authorizeRequests()
                                    .mvcMatchers("/generate-token", "/user/").permitAll()
                                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                                    .anyRequest().authenticated()
                                    .and()

                )*/



                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                //.formLogin(withDefaults());
                return http.build();

    }

    /*@Bean

    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();

}*/

    @Bean

    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();

    }


    }
