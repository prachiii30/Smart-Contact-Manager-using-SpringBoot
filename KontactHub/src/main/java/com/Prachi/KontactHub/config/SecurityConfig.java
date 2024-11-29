package com.Prachi.KontactHub.config;


import com.Prachi.KontactHub.Services.Implementation.SecurityCustomUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


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
        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/user/**").authenticated();
//            authorize.requestMatchers("/home","/register","/services").permitAll(); // Allow POST requests for authentication
            authorize.anyRequest().permitAll();
        });
//        khud ka login form
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate"); // POST request is expected here
            formLogin.successForwardUrl("/user/dashboard");
//            formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");


        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        httpSecurity.oauth2Login(oauth->{
            oauth.loginPage("/login");
        });
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
