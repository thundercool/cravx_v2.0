package com.astrika.abg.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.astrika.abg.service.impl.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Inside HttpSecurity Method");
		 http
			 .csrf().disable()
	         .authorizeRequests()
	             .antMatchers("/resources/**","/ForgotPassword","/SubmitForgotPassword","/ResetPassword","/SubmitResetPassword","/Register","/SaveRegisteration").permitAll()
	             .anyRequest().authenticated()
	             .and()
	         .formLogin()
	             .loginPage("/login").permitAll()
	 	         /*.failureHandler(failureHandler())*/
	 	         .successHandler(successHandler())
	             .and()
	         .logout()
	             .logoutUrl("logout")
	         	 .logoutSuccessUrl("/login?logout")
	         	 .invalidateHttpSession(true)
	             .permitAll();
	}
	
	@Bean
	public SimpleUrlAuthenticationSuccessHandler  successHandler() {
		SimpleUrlAuthenticationSuccessHandler  successHandler = new SimpleUrlAuthenticationSuccessHandler ();
	    successHandler.setTargetUrlParameter("/Index");
	    System.out.println("Target Success Url Called");
	    return successHandler;
	}
	
	@Bean
	public SimpleUrlAuthenticationFailureHandler failureHandler() {
		SimpleUrlAuthenticationFailureHandler failureHandler=new SimpleUrlAuthenticationFailureHandler("/login?error");
		System.out.println("Target Failure Url Called");
		return failureHandler;
	}
	
}
