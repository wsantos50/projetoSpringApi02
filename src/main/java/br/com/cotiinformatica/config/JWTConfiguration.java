package br.com.cotiinformatica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.cotiinformatica.filters.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class JWTConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		//definindo quais serviços da API não exigem autenticação
		.antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
		.antMatchers(HttpMethod.POST, "/api/login").permitAll()
		//todo o restante da API exige autenticação!
		.anyRequest().authenticated();		
	}
}