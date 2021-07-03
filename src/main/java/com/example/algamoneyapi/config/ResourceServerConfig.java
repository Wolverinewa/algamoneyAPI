package com.example.algamoneyapi.config;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * Antes da versão 5 do Spring, não era necessário explicitar qual PasswordEncoder 
		 * utilizar para “decodificar” a senha de usuário. Por padrão o Spring nos fornecia 
		 * uma classe chama NoOpPasswordEncoder, que na verdade não realizaria nenhuma “decodificação”.
		 */
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ROLE");
		
		/*
		 * Podemos passar entre chaves o ID do Encoder que desejamos utilizar, como a senha não está 
		 * criptografada, vamos utilizar o {noop}. Caso nossa senha estivesse criptografa com BCrypt (por exemplo), 
		 * poderíamos utilizar {bcrypt}
		 */
		///auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ROLE");
		
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/categorias").permitAll()
				.anyRequest().authenticated()
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.csrf().disable();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}
	
	/*
	@Bean
	public UserDetailsService userDetailsService() {
	*/
		/*
		 * Para este caso, criamos as informações de usuário, criando um Bean UserDetailsService, que já nos 
		 * devolve uma instância com o PasswordEncoder padrão. Nota-se que este método está depreciado, pois 
		 * não é algo considerado seguro, apenas para demonstrações
		 */
/*
		User.UserBuilder builder = User.withDefaultPasswordEncoder();
	    
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    
		manager.createUser(builder.username("admin").password("admin").roles("ROLE").build());
	    
		return manager;
	}
*/
}
