package com.mysearch.work.config;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mysearch.work.service.MemberDetailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity 
@Configuration 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final MemberDetailService memberDetailservice;
	
	@Override
    public void configure(WebSecurity web) { // 4
	    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests() 
				// user 페이지 설정
				.antMatchers("/search/**").authenticated()// 로그인 필요 
				.antMatchers("/my/**").authenticated()// 로그인 필요 
				.antMatchers("/**").permitAll()
				.and() // 로그인 페이지 사용
			.formLogin()
				.loginPage("/login/form") // 로그인 페이지 경로 설정 
				.loginProcessingUrl("/login") // 로그인이 실제 이루어지는 곳
				.usernameParameter("username")
                .passwordParameter("password")
                .successHandler( new AuthenticationSuccessHandler() { // 로그인 성공시 호출					
					@Override
					public void onAuthenticationSuccess(
								HttpServletRequest request, 
								HttpServletResponse response,
								Authentication authentication) throws IOException, ServletException {
						
						response.sendRedirect("/");
					}
				})
				.failureHandler( new AuthenticationFailureHandler() {  // 로그인 실패시 호출
					
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							org.springframework.security.core.AuthenticationException exception)
							throws IOException, ServletException {
						response.sendRedirect("/login/form");
					}
				})
                .permitAll();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailservice);
    }


		
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


	
}
