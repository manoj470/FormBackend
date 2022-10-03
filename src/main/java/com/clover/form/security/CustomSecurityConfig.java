package com.clover.form.security;
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
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.crypto.password.NoOpPasswordEncoder;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
 public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

 @Autowired
 private MyUserDetailsService myUserDetailsService;
 @Autowired
 private AuthEntryPointJwt unauthorizedHandler;

// @Bean
// public AuthTokenFilter authenticationJwtTokenFilter() {
//  return new AuthTokenFilter();
// }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
   System.out.println("configure method in config file...."+auth);
  }

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  System.out.println("configure http Method called...");
//     http.cors().and().csrf().disable().authorizeRequests()
//             .antMatchers("/emp/auth/login","/emp/count").permitAll()
//             .anyRequest().authenticated();
     http.cors().and().csrf().disable()
             .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
             .authorizeRequests().antMatchers("/emp/auth/sign_in").permitAll()
             .anyRequest().authenticated();
//     http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
 }

 @Override
 @Bean
 public AuthenticationManager authenticationManagerBean() throws Exception {
  return super.authenticationManagerBean();
 }

 @Bean
 public PasswordEncoder passwordEncoder() {
  return NoOpPasswordEncoder.getInstance();
 }

 }
