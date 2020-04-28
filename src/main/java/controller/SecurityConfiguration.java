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
	 http.authorizeRequests().antMatchers("/tablon/noticia/*").permitAll();
	 http.authorizeRequests().antMatchers("/tablon/noticia").permitAll();
	 http.authorizeRequests().antMatchers("/**/*.js", "/**/*.css").permitAll();
	 //http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
	 
	 // Private pages (all other pages)
	 http.authorizeRequests().anyRequest().authenticated();


	 
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
