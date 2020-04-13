package controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {

	 // Public pages
	 http.authorizeRequests().antMatchers("/").permitAll();
	 http.authorizeRequests().antMatchers("/login").permitAll();
	 http.authorizeRequests().antMatchers("/loginError").permitAll();
	 http.authorizeRequests().antMatchers("/logout").permitAll();
	 http.authorizeRequests().antMatchers("/**/*.js", "/**/*.css").permitAll();
	 
	 // Private pages (all other pages)
	 http.authorizeRequests().anyRequest().authenticated();
	 
	 // Login form
	 http.formLogin().loginPage("/login");
	 http.formLogin().usernameParameter("username");
	 http.formLogin().passwordParameter("password");
	 http.formLogin().defaultSuccessUrl("/socios/socios.html");
	 http.formLogin().failureUrl("/loginError");
	 
	 // Logout
	 http.logout().logoutUrl("/logout");
	 http.logout().logoutSuccessUrl("/");

	 // Disable CSRF at the moment
	 http.csrf().disable();
	 
	}
	
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	 // User cargado en memoria
	 auth.inMemoryAuthentication().withUser("user").password("{noop}pass").roles("USER");
	 }
}
