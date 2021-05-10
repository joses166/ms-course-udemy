package com.udemy.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = { "/hr-oauth/oauth/token" };
	private static final String[] OPERATOR = { "/hr-worker/**" };
	private static final String[] ADMIN = { "/hr-payroll/**", "/hr-user/**" };
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
			.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(PUBLIC).permitAll() // qualquer um pode acessar a rota publica
			.antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR", "ADMIN") // rotas do operador pode ser acessadas por GET pelas roles ADMIN e OPERATOR
			.antMatchers(ADMIN).hasRole("ADMIN") // todas as rotas de administrador deve ser acessada por usuário com role ADMIN
			.anyRequest().authenticated(); // qualquer outra rota o usuário deve estar autenticado
	}

	
	
}
