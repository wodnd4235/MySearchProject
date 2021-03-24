package com.mysearch.work.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberLoginController {

	
	/**
	 * 로그인 화면
	 * 
	* @methodName : userAccess
	* @author : cju  
	* @date : 2021.03.21 
	* @param request
	* @return
	 */
	@GetMapping("/login/form")
    public String userAccess(HttpServletRequest request) {
		
		AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
		// 로그인 했을경우 튕김
		if (!trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
			return "redirect:/";
		}
		
		return "member/login";
    }
}

