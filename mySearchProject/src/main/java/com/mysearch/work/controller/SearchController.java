package com.mysearch.work.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mysearch.work.dto.AreaDto;
import com.mysearch.work.entity.SearchHistory;
import com.mysearch.work.repository.SearchHistoryRepository;
import com.mysearch.work.service.KeyWordSearchService;
import com.mysearch.work.vaild.VaildService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RestController
public class SearchController {
	
	@Autowired
	private KeyWordSearchService keyWordSearchService;
	@Autowired
	private SearchHistoryRepository searchHistoryRepository;
	
	
	@GetMapping("/search")
	public ModelAndView main() {
		ModelAndView mv  = new ModelAndView();
		// 많이 검색한 검색어 10개조회	
		List<SearchHistory> searchTopList = searchHistoryRepository.findTop10ByOrderByCntDesc();
		mv.addObject("searchTop", searchTopList);
		mv.setViewName("/search/search");
		return mv;
	}
	
	/**
	 * 키워드 검색 
	* @methodName : searchArea
	* @author : cju  
	* @date : 2021.03.21 
	* @param area
	* @param errors
	* @param model
	* @return
	* @throws Exception
	 */
	@PostMapping("/search/area")
	public Object searchArea(@Valid AreaDto area, Errors errors) throws Exception{
		
		List<Object> placeList = new ArrayList<>();
		
		// 유효성 검증
		if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메시지를 핸들링
            throw new NullPointerException("검색어를 입력해 주세요");
	    } 
		
		try {
			// 카카오, 네이버 검색
			placeList = keyWordSearchService.getSearchPlace(area);
		}catch(Exception e) {
			e.printStackTrace();
			log.error("장소 검색중 에러 {}",e.getMessage());
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("placeList", placeList);
		return map;
		
	}
	
}
