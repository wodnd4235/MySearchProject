package com.mysearch.work.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.mysearch.work.common.KakaoRestApiHelper;
import com.mysearch.work.dto.AreaDto;
import com.mysearch.work.dto.KaKaoSearch;

import lombok.extern.slf4j.Slf4j;

/**
 * 카카오 키워드 검색 서비스
 * @author chlwo
 *
 */
@Slf4j
@Service
public class KakaoKeywordService {
	
	@Autowired
	private KakaoRestApiHelper kakaoRestApiHelper;
	
	public List<KaKaoSearch> kakaoSearch(AreaDto area) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<KaKaoSearch> kakaoSearchList = new ArrayList<>();
		String json;
		
		// json 데이터 가공
		try {
			
			// 카카오 검색
			ResponseEntity<String> data = kakaoRestApiHelper.getSearchPlaceByKeyword(area);
			
			// null 체크 및 json 데이터 형식 가공
			if(Objects.nonNull(data)) {
				json = objectMapper.writeValueAsString(data);
				Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
				Map<String, Object> map1 = objectMapper.readValue(map.get("body").toString(), new TypeReference<Map<String,Object>>(){});
				json = objectMapper.writeValueAsString(map1.get("documents"));
				kakaoSearchList = objectMapper.readValue(json, new TypeReference<List<KaKaoSearch>>(){});
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("카카오 검색도중 에러=====>>{}",e.getMessage());
		}
		

		return kakaoSearchList;
	}
	
	public List<String> getKaKaoSearch(AreaDto area) throws Exception{
		
		List<KaKaoSearch> list = this.kakaoSearch(area);
		
		// api 결과값중 장소값만 추출하여 재정렬
		List<String> kakaoList = Lists.transform(list, new Function<KaKaoSearch, String>() {
			@Override
			public String apply(KaKaoSearch search) {
				return search.getPlace_name();
			} 
		}); 
		
		return kakaoList;
	}

}
