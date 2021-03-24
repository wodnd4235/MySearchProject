package com.mysearch.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysearch.work.entity.History;
import com.mysearch.work.member.LoginManager;
import com.mysearch.work.repository.HistoryRepository;

@Controller
@RequestMapping("/my")
public class MySearchHistoryController {
	
	@Autowired
	private HistoryRepository historyRepository;
	
	/**
	 * 내검색목록
	* @methodName : main
	* @author : cju  
	* @date : 2021.03.22 
	* @return
	 */
	@GetMapping("/search")
	public ModelAndView main() {
		List<History> mySerachList = historyRepository.findByMbrIdOrderByCreatedDateDesc(LoginManager.getId());
		ModelAndView mv = new ModelAndView();
		mv.addObject("mySerachList", mySerachList);
		mv.setViewName("/search/mySearchList");
		return mv;
	}
	
}
