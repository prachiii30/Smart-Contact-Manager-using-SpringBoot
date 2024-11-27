package com.Prachi.KontactHub.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider ();

        //User detail service ka object


        daoAuthenticationProvider.setUserDetailsService(null);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return  daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
