package org.ecommerce.web.admincontroller;

import java.util.ArrayList;
import java.util.List;

import org.ecommerce.persistence.models.AuthorityEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * @author sergio
 */
//@Component
//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableWebSecurity
//@ComponentScan(value = { "org.ecommerce.security" })
public class SecurityConfig1 extends GlobalAuthenticationConfigurerAdapter {

	@Autowired(required=true)
	@Qualifier("userDetailsService1")
	private UserDetailsService userDetailsService1;

	//@Autowired
	//private CustomLogoutHandler1 logoutHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationEventPublisher(authenticationEventPublisher()).userDetailsService(userDetailsService1)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public DefaultAuthenticationEventPublisher authenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}

	/**
	 * Security Configuration for Admin zone
	 */
	@Configuration
	@Order(1)
	public class AdminConfiguration extends WebSecurityConfigurerAdapter {
		//private static final Logger logger = LoggerFactory.getLogger(AdminConfiguration.class);

		@Autowired(required=true)
		@Qualifier("successHandler1")

		private AuthenticationSuccessHandler successHandler1;

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			//logger.debug("login admin home controller debug");
			//logger.info("login admin home controller debug");
			//http.csrf().disable().
			http.
			requestMatchers().antMatchers("/admin/**").and().authorizeRequests()
					.antMatchers("/admin/users/self/**").fullyAuthenticated().antMatchers("/admin/**")
					.hasAuthority(AuthorityEnum.ROLE_ADMIN.name()).and().formLogin().loginPage("/admin/login")
					.usernameParameter("username").passwordParameter("password").successHandler(successHandler1)
					.permitAll().and().logout()//.addLogoutHandler(logoutHandler)
					.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
					.logoutSuccessUrl("/admin/login?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true)
					.and().exceptionHandling().accessDeniedPage("/admin/403").and().csrf().disable();
		}
	/*	@Override
	   @Bean
	   public UserDetailsService userDetailsService() {
	       List<UserDetails> users= new ArrayList<UserDetails>();
	    //  users.add( User.withUsername("user")
          // .password("password")
          // .roles("ADMIN")
          //.build());
	       users.add(User.withDefaultPasswordEncoder().username("admin").password("nimda").roles("USER","ADMIN").build());
	        users.add(User.withDefaultPasswordEncoder().username("Spring").password("Security").roles("USER").build());
	       return new InMemoryUserDetailsManager(users);
	   }
	}*/
	}
	/**
	 * Security Configuration for Frontend zone
	 */
	@Configuration
	@Order(2)
	public class FrontendConfiguration extends WebSecurityConfigurerAdapter {}

	@Configuration
	@Order(3)
	public class GlobalWebConfiguration extends WebSecurityConfigurerAdapter {}
}
