package com.tortaza.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tortaza.app.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication().withUser("alex").password("{noop}base123").roles("USER");
		auth.inMemoryAuthentication().withUser("nelson").password("{noop}base1234").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("ale").password("{noop}base12").roles("USER","ADMIN");*/
		
		auth.userDetailsService(usuarioService);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

//permiso
		http.authorizeRequests()
		    .antMatchers("/producto/listar").access("hasRole('USER')")
		    .antMatchers("/producto/registrar").access("hasRole('ADMIN')")
		    .antMatchers("/producto/editar/*").access("hasRole('USER') and hasRole('ADMIN')")
		    .antMatchers("/producto/borrar/*").access("hasRole('USER') and hasRole('ADMIN')");
		http.authorizeRequests().and()
		.csrf().disable();
		
		http.authorizeRequests().and()
		.httpBasic();
		
		http.authorizeRequests().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
