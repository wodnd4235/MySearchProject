package com.mysearch.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysearch.work.member.LoginManager;

import lombok.extern.slf4j.Slf4j;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("isLogin",LoginManager.isLogin());
		mv.setViewName("/main/main");
		return mv;
	}
	
}
