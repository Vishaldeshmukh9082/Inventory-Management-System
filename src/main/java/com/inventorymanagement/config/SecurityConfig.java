
package com.inventorymanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.inventorymanagement.services.impl.SecurityCustomUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {

   // @Bean
   // public UserDetailsService UserDetailsService(){

   // //for only to save and check user details from memory

   // UserDetails user=User.withDefaultPasswordEncoder()
   // .username("admin")
   // .password("12345")
   // .roles("ADMIN","USER")
   // .build()
   // ;
   // var InMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
   // return InMemoryUserDetailsManager;

   // }

   /* for validate user details with database */

   @Autowired
   private SecurityCustomUserDetailService userDetailService;

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

      httpSecurity.authorizeHttpRequests(authorize -> {
         // Only users with "ROLE_ADMIN" can access URLs under "/admin/**"
         authorize.requestMatchers("/admin/**").hasRole("ADMIN");
         
         // Only users with "ROLE_USER" can access URLs under "/user/**"
         authorize.requestMatchers("/user/**").hasRole("USER");
     
         // Allow any user to access other URLs
         authorize.anyRequest().permitAll();
     });
     
     httpSecurity.formLogin(formLogin -> {
         formLogin.loginPage("/login");
         formLogin.loginProcessingUrl("/authanticate");
         
         // Redirect based on role after successful login
         formLogin.successHandler((request, response, authentication) -> {
             if (authentication.getAuthorities().stream()
                 .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                 response.sendRedirect("/admin/home");
             } else if (authentication.getAuthorities().stream()
                 .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                 response.sendRedirect("/user/home");
             } else {
                 response.sendRedirect("/home"); // Default redirect
             }
         });
     
         formLogin.usernameParameter("email");
         formLogin.passwordParameter("password");
     });
     
     httpSecurity.logout(logoutForm -> {
         logoutForm.logoutUrl("/do-logout");
         logoutForm.logoutSuccessUrl("/login?logout=true");
     });
     
     
      return httpSecurity.build();
   }


   @Bean
   public AuthenticationProvider authenticationProvider() {

      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

      // user details object
      daoAuthenticationProvider.setUserDetailsService(userDetailService);

      // password encoder object
      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
      return daoAuthenticationProvider;
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

}
