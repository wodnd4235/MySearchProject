package com.mysearch.work.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.mysearch.work.common.NaverRestApiHelper;
import com.mysearch.work.dto.AreaDto;
import com.mysearch.work.dto.NaverSearch;

import lombok.extern.slf4j.Slf4j;

/**
 * 네이버 검색어 api 서비스
 * @author chlwo
 *
 */
@Slf4j
@Service
public class NaverKeywordService {
	
	@Autowired
	private NaverRestApiHelper naverRestApiHelper;
	
	public List<NaverSearch> naverSearch(AreaDto area) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<NaverSearch> NaverSearchList = new ArrayList<>();
		String json;
		
		// json 데이터 가공
		try {
			
			// 카카오 검색
			String data = naverRestApiHelper.getSearchPlaceByKeyword(area);
			
			// null체크 및 json 응답 데이터 가공
			if(Objects.nonNull(data)) {
				Map<String, Object> map = objectMapper.readValue(data, new TypeReference<Map<String,Object>>(){});
				json = objectMapper.writeValueAsString(map.get("items"));
				NaverSearchList = objectMapper.readValue(json, new TypeReference<List<NaverSearch>>(){});
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("네이버 검색도중 에러=====>>{}",e.getMessage());
		}
		
		return NaverSearchList;
	}

	
	public List<String> getNaverSearch(AreaDto area) throws Exception{
		
		List<NaverSearch> list = this.naverSearch(area);
		
		// api 결과값중 장소값만 추출하여 재정렬
		List<String> kakaoList = Lists.transform(list, new Function<NaverSearch, String>() {
			@Override
			public String apply(NaverSearch search) {
				return search.getTitle();
			} 
		}); 
		return kakaoList;
	}
}
