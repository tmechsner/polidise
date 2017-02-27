package org.polidise.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Matthias on 4/20/16.
 * Handles the setup for Spring-Security-Framework
 * in a straight forward manner.
 */
@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	public BasicSecurityConfig() {

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
                /* Allow access to static content for everyone*/
			.antMatchers("/css/**").permitAll()
			.antMatchers("/error/**").permitAll()
			.antMatchers("/fonts/**").permitAll()
			.antMatchers("/img/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/impressum").permitAll()
			.antMatchers("/**").permitAll()

                /* For everything else you need to authorize yourself */
			.anyRequest().hasAnyAuthority("USER", "PREMIUM_USER", "ADMIN")

                /* The login-form is allowed for everybody */
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()

			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
			.permitAll();
	}

	/**
	 * Global security configuration.
	 *
	 * @param userSecurityService    Service handling users
	 * @param passEncoder password encoder
	 * @param auth        authentication manager builder
	 * @throws Exception Some spring exceptions
	 */
	@Autowired
	public void configureGlobal(UserSecurityService userSecurityService,
			PasswordEncoder passEncoder,
			AuthenticationManagerBuilder auth) throws Exception {
		LoginInfo.setLoginProvider(new SpringLoginProvider());
		auth
			.userDetailsService(userSecurityService)
			.passwordEncoder(passEncoder);
	}


	@Bean PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public String encodePassword(String password) {
		return passwordEncoder().encode(password);
	}

}
