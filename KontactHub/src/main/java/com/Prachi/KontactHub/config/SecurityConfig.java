package com.Prachi.KontactHub.config;


import com.Prachi.KontactHub.Services.Implementation.SecurityCustomUserDetailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    //user create and login using java code with in memory service
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//       UserDetails user= User
//               .withDefaultPasswordEncoder()
//               .username("admin123")
//               .password("12345")
//               .roles("ADMIN","USER")
//               .build();
//
//        UserDetails user2= User
//                .withDefaultPasswordEncoder()
//                .username("user123")
//                .password("12345")
//
//                .build();
//        var inMemoryUserDetailManager= new InMemoryUserDetailsManager(user);
//        return inMemoryUserDetailManager;
//
//    }
    @Autowired
    private SecurityCustomUserDetailService userDetailService;



    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider ();

        //User detail service ka object


        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return  daoAuthenticationProvider;
    }


//    Spring Security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        configuration

//        Configured Urls which private which public
        httpSecurity.authorizeHttpRequests(authorize->{
//            authorize.requestMatchers("/login", "/authenticate", "/register", "/home").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

//        khud ka login form

        httpSecurity.formLogin(formLogin-> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/dashboard");
            formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

        });



        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
