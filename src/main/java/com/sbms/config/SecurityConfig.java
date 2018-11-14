/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.config;

/**
 *
 * @author Roy
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfig{

   @Autowired
   private UserDetailsService userDetailsService;

       

     /*  private PasswordEncoder getPasswordEncoder(){
           return new PasswordEncoder() {
           @Override
           public String encode(CharSequence cs) {
               return cs.toString();
           }

           @Override
           public boolean matches(CharSequence cs, String string) {
               return true;
           }
       };
       }*/
    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.antMatcher("/rest/**").authorizeRequests().anyRequest().hasAnyAuthority("GLOBAL", "SUPER_ADMIN", "ADMIN", "USER") .and().httpBasic();
        }
    }
    @Configuration
    @Order(2)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
                     http.authorizeRequests()
                   .antMatchers("/", "/login").permitAll()
                //   .antMatchers("/admin/gb/**").hasAuthority("GLOBAL")          
                        .antMatchers("/admin/gb/**").hasAnyAuthority("GLOBAL", "SUPER_ADMIN")      
                  .antMatchers("/admin/**").hasAnyAuthority("GLOBAL", "SUPER_ADMIN", "ADMIN")
                   .antMatchers("/user/**").hasAnyAuthority("GLOBAL", "SUPER_ADMIN", "ADMIN", "USER")
                   .antMatchers("/client/**").hasAnyAuthority("GLOBAL", "SUPER_ADMIN", "ADMIN", "USER") 
                   
                //   .anyRequest().authenticated()
                   .and()
                    .formLogin().loginPage("/login").defaultSuccessUrl("/home").usernameParameter("username")
                                    .passwordParameter("password").failureUrl("/loginfailed").and().logout()
                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                    .logoutSuccessUrl("/login").and().exceptionHandling()
                                    .accessDeniedPage("/access-denied").and().sessionManagement().maximumSessions(1).expiredUrl("/login").and().invalidSessionUrl("/login");
        }
        @Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/password/**");
	}
        
    }
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {          
        auth.userDetailsService(userDetailsService);
        }
        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider
                    = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService);
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder;
        }
  
    
}
