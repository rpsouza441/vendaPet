package br.com.lojapet.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.lojapet.persistence.repository.springsecurity.UserUserDetailsService;




@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserUserDetailsService userUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//primeiro bloqueio depois liberar
		http.authorizeRequests()
	    .antMatchers("/resources/**").permitAll()
	    .anyRequest().authenticated()
//	    .antMatchers("/url-magica-maluca-qpuioywkbliughOKJNKJQ872tyqewvtf71367").permitAll()
//	    .antMatchers("/").permitAll()
	    .and().formLogin().loginPage("/login").permitAll()
	    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    .logoutSuccessUrl("/");
	} 
		
		
		
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}