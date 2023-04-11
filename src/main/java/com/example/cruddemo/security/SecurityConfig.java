package com.example.cruddemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }


//public class SecurityConfig  {

//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().
//									withUser("user")
//									.password("myuser")
//									.roles("USER")
//									.and()
//									.withUser("owner")
//									.password("shop")
//									.roles("OWNER")
//									.and()
//									.withUser("admin")
//									.password("salon")
//									.roles("ADMIN");
//	}
	@Bean
	PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
	}
}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.authorizeRequests()
//								.antMatchers("/api/user/16").hasAnyRole("USER","ADMIN")
//								.antMatchers("/api/shops").hasAnyRole("OWNER","ADMIN")
//								.antMatchers("/api").permitAll()
//								.and().formLogin();
//								
//	}
//	

//}
