package com.Teste.Aplication.config;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// acessos publicos liberados
		http.authorizeRequests()
		   .antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
		   .antMatchers("/", "/home").permitAll()
		  // .antMatchers("/user/**").permitAll()///////////
		   
		   .anyRequest().authenticated()
		   
		   //.and().formLogin().loginPage("/entrar").permitAll()///////////
		   //.successForwardUrl("/home").and().logout().permitAll()/////////////
		   
		   .and()
		       .formLogin()
		       .loginPage("/login")
		       .defaultSuccessUrl("/", true)
		       .failureUrl("/login-error")
		       .permitAll()
		    .and()
		       .logout()
		       .logoutSuccessUrl("/")
		    .and()
				.exceptionHandling()
				.accessDeniedPage("/acesso-negado")
			.and()
				.rememberMe();
		
	}

	@Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/*@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**",  "/distribuition/**", "/fonts/**", "/vendor/**", "/img/**",  "/js/**",  "/scss/**", "/h2/**");
		web.ignoring().antMatchers("/layout", "http::/**", "https::/**", "/http::/**", "/https::/**", "/template-login/**");
  
 }*/

	
	
}
