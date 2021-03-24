package com.example.demo.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.auth.ApplicationUserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;
	
	@Autowired	
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
	}

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails mica = User.builder()
//			.username("mica")
//			.password(passwordEncoder.encode("password"))
//			//.roles(ApplicationUserRole.STUDENT.name())
//			.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
//			.build();
//		
//		UserDetails cale = User.builder()
//				.username("cale")
//				.password(passwordEncoder.encode("password"))
//				//.roles(ApplicationUserRole.ADMIN.name())
//				.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
//				.build();
//				
//		
//		UserDetails bransa = User.builder()
//				.username("bransa")
//				.password(passwordEncoder.encode("password"))
//				//.roles(ApplicationUserRole.ADMINTRAINEE.name())
//				.authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities())
//				.build();
//				
//		
//		return new InMemoryUserDetailsManager(mica, cale, bransa);
//	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		//.and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
//		.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//		.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//		.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//		.antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name())
		.anyRequest()
		.authenticated()
		.and()
		//.httpBasic();
		.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/courses", true)
			.passwordParameter("password")
			.usernameParameter("username")
		.and()
		.rememberMe()
			.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
			.key("somethingverysecure")
			.rememberMeParameter("remember-me")
		.and()
		.logout()
			.logoutUrl("/logout")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //should be POST for logout, it must be post if csrf is on
			.clearAuthentication(true)
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID", "remember-me")
			.logoutSuccessUrl("/login");
		
	}

}
