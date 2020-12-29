package com.Teste.Aplication.config;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
		 .antMatchers("/fragments/**").permitAll()
		   .antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
		   .antMatchers("/home","/user/detalhes/id").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("/compra/**").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("/compra/pagamento**").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("/user/cadastrar/cadastro").permitAll()
		   .antMatchers("/user/cadastrar/").permitAll()
		   .antMatchers("/api/**").permitAll()
		   .antMatchers("/", "/user/detalhes").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("/user/detalhes","/user/editar").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("/user/**").hasAnyAuthority("USER","ADMIN")
		   //.antMatchers("/user/editarSenha").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("/compras/**").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("/compras/comprar/**").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("/cartao/**").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("http://**").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("https://**").hasAnyAuthority("USER","ADMIN")
		   .antMatchers("/user/**").permitAll()
		   //.antMatchers("/swagger-ui").permitAll() 
		   .antMatchers("/swagger-ui.html").permitAll()
		   .antMatchers("/swagger-resources/**").permitAll()
		   .antMatchers("/v2/api-docs").permitAll()
		   .antMatchers("/webjars/**").permitAll()
		   .antMatchers("classpath:/META-INF/resources/webjars/").permitAll()
		   .antMatchers("classpath:/META-INF/resources/").permitAll()
		   
		  	   
		   .antMatchers(HttpMethod.POST, "/user/salvar").permitAll()
		   .antMatchers(HttpMethod.GET, "/").permitAll()
		   .antMatchers(HttpMethod.GET, "/cadastrar").permitAll()
		   .anyRequest().authenticated()
		   
		   .antMatchers("layout").permitAll()
		   .antMatchers("/static/**").permitAll()
		   .antMatchers("/resources/**").permitAll()
		   .antMatchers("/css/**").permitAll()
		   .antMatchers("/js/**").permitAll()
		   
		   .anyRequest().authenticated()    
		   
		   //.antMatchers("/user/**").permitAll()///////////
		   
		   
		   
		   ///.and().formLogin().loginPage("/entrar").permitAll()///////////
		   ///.successForwardUrl("/home").and().logout().permitAll()/////////////
		   
		   .and()
		       .formLogin()
		       .loginPage("/").permitAll()
		       .successForwardUrl("/home")
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
		http.csrf().disable();
		
	}

	@Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	
}
