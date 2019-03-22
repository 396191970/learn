package com.tuobuxie.demo.sercurity.conf;

import com.tuobuxie.demo.sercurity.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class AuthConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthUserService authUserService;


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
		.antMatchers("/css/**","/staic/**", "/js/**","/images/**").permitAll()
		.antMatchers("/", "/login","/session_expired").permitAll()
           .and()
     .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/main_menu")
        .failureUrl("/loginError")
		.usernameParameter("txtUserCd")
		.passwordParameter("txtUserPwd")
        .permitAll()
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/")
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.permitAll()
		.and()
		.sessionManagement()
		.invalidSessionUrl("/session_expired");
//		.maximumSessions(4)
//		.maxSessionsPreventsLogin(true)
//		.expiredUrl("/session_expired");
		httpSecurity.logout().permitAll();

	}

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("111"));
		System.out.println("{bcrypt}"+bCryptPasswordEncoder.encode("111"));
		System.out.println(bCryptPasswordEncoder.matches("111","$2a$10$KBcKG2hC3yhU6gt3595UJOyxrw4Hc5rolRFbpDYFWezsg7bWR/YA."));

	}
}
