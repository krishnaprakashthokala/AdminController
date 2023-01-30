package org.ecommerce.web.admincontroller;

import org.ecommerce.persistence.models.AuthorityEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//	@EnableWebSecurity
	public class TempConfig extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {    
	       /* http
	            .csrf().disable()
	            .requestMatchers().antMatchers("/admin/**").and().authorizeRequests()
	                .anyRequest().permitAll();*/
	    	http.
			requestMatchers().antMatchers("/admin/**").and().authorizeRequests()
					.antMatchers("/admin/users/self/**").fullyAuthenticated().antMatchers("/admin/**")
					.hasAuthority(AuthorityEnum.ROLE_ADMIN.name()).and().formLogin().loginPage("/admin/login")
					.usernameParameter("username").passwordParameter("password")//.successHandler(successHandler1)
					.permitAll().and().logout()//.addLogoutHandler(logoutHandler)
					.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
					.logoutSuccessUrl("/admin/login?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true)
					.and().exceptionHandling().accessDeniedPage("/admin/403").and().csrf().disable();
	        }
	}


