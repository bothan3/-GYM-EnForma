package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	 public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {

	 // Public pages
	 http.authorizeRequests().antMatchers("/").permitAll();
	 http.authorizeRequests().antMatchers("/login").permitAll();
	 http.authorizeRequests().antMatchers("/logout").permitAll();
	 http.authorizeRequests().antMatchers("/socios/altaSocio*").permitAll();
	 http.authorizeRequests().antMatchers("/tablon/noticia/*").permitAll();
	 http.authorizeRequests().antMatchers("/tablon/noticia").permitAll();
	 http.authorizeRequests().antMatchers("/validacion").permitAll();
	 http.authorizeRequests().antMatchers("/socios/alta").permitAll();
	 http.authorizeRequests().antMatchers("/cargar").permitAll();


	 http.authorizeRequests().antMatchers("/**/*.js", "/**/*.css").permitAll();
	 //http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
	 
	 //Privates pages
	 //http.authorizeRequests().anyRequest().authenticated();
	 http.authorizeRequests().antMatchers("/admin.html" ).hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/socios/filtros.html" ).hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/socios/listadoSocios" ).hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/socios/listadoSocios/*" ).hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/socios/listadoSocios/*" ).hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/socios/listadoSocios/*" ).hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/socios/listadoSocios/*" ).hasRole("ADMIN");

	 http.authorizeRequests().antMatchers("/profesores/*").hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/profesores/*/*").hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/clases/*").hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/clases/*/*").hasRole("ADMIN");
	 http.authorizeRequests().antMatchers("/tablon/nuevaNoticia.html").hasRole("ADMIN");

	 
	 http.authorizeRequests().antMatchers("/tablon/noticia" ).hasAnyRole("USER","ADMIN");
	 http.authorizeRequests().antMatchers("/socios/confirmacionSocio*" ).hasRole("USER");
	 http.authorizeRequests().antMatchers("/socios/busquedaPersonalizada" ).hasAnyRole("USER","ADMIN");
	 http.authorizeRequests().antMatchers("/socios/apuntarClase/*" ).hasAnyRole("USER","ADMIN");
	 http.authorizeRequests().antMatchers("/socios/apuntarClase" ).hasAnyRole("USER","ADMIN");	 
	 http.authorizeRequests().antMatchers("/socios/desapuntarClase/*" ).hasAnyRole("USER","ADMIN");
	 http.authorizeRequests().antMatchers("/socios/desapuntarClase" ).hasAnyRole("USER","ADMIN");
	 
	 // Login form
	 http.formLogin().loginPage("/login");
	 http.formLogin().usernameParameter("username");
	 http.formLogin().passwordParameter("password");
	 http.formLogin().defaultSuccessUrl("/");
	 http.formLogin().failureUrl("/loginerror");
	 
	 // Logout
	 http.logout().logoutUrl("/logout");
	 http.logout().logoutSuccessUrl("/");

	 // Disable CSRF at the moment
	 http.csrf().disable();
	 
	}
	
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		 // Database authentication provider
	     auth.authenticationProvider(authenticationProvider);
	 }
}
