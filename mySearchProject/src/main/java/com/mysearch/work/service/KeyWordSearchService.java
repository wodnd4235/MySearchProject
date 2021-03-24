package com.mysearch.work.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.mysearch.work.dto.AreaDto;
import com.mysearch.work.dto.HistoryDto;
import com.mysearch.work.dto.SearchHistoryDto;
import com.mysearch.work.entity.History;
import com.mysearch.work.entity.SearchHistory;
import com.mysearch.work.member.LoginManager;
import com.mysearch.work.repository.HistoryRepository;
import com.mysearch.work.repository.SearchHistoryRepository;

@Service
public class KeyWordSearchService {
	
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private SearchHistoryRepository searchHistoryRepository;
	@Autowired
	private KakaoKeywordService kakaoKeywordService;
	@Autowired
	private NaverKeywordService naverKeywordService;
	
	
	@Transactional
	public List<Object> getSearchPlace(AreaDto area) throws Exception{
		List<Object> result = new ArrayList<>();
		
		// 카카오 검색 장송이름 리스트
		List<String> kakaoList = this.kakaoKeywordService.getKaKaoSearch(area);
		// 네이버 검색 장소이름 리스트 
		List<String> naverList = this.naverKeywordService.getNaverSearch(area);
		
		// 검색된 결과 순서 재정렬 
		if(!CollectionUtils.isEmpty(kakaoList) || !CollectionUtils.isEmpty(naverList)) {
			result = this.setAreaList(kakaoList, naverList);
		}
		
		// top10 검색을 위한 키워드 검색 횟수 및 키워드 저장 또는 수정
		this.createKeywordHistoty(area.getKeyword());
		
		// 내검색 히스토리 등록
		this.createHistory(area.getKeyword());
		
		return result;
	}
	
	/**
	 * 중복값 비교 시작
	 * @methodName : setAreaList
	 * @author : cju  
	 * @date : 2021.03.22 
	 * @param kakaoList
	 * @param naverList
	 * @return
	 */
	@Transactional
	public List<Object> setAreaList(List<?> kakaoList, List<?> naverList){
		
		List<Object> result = new ArrayList<>();
	
		/**
		 * 중복값 비교 null 체크
		 * 1. 둘다 값이 있을경우
		 * 2. 둘중 하난 값이 있으면 합침.
		 */
		{
			
			if(!CollectionUtils.isEmpty(kakaoList) && !CollectionUtils.isEmpty(naverList)) {
				result = this.keyWordCompare(kakaoList, naverList);
			}else {
				result = Stream.concat(kakaoList.stream(), naverList.stream()).collect(Collectors.toList());
			}
			
		}
		
		return result;
	}
	
	/**
	 * 중복결과 정렬 로직
	 * 
	 * @methodName : keyWordCompare
	 * @author : cju  
	 * @date : 2021.03.22 
	 * @param kakaoList
	 * @param naverList
	 * @return
	 */
	public List<Object> keyWordCompare(List<?> kakaoList, List<?> naverList){
		
		List<Object> result = new ArrayList<>();
		Iterator<?> kakao = kakaoList.iterator();
		
		/**
		 *  카카오 네이버 중복 검색결과 기준으로 비교 로직
		 *  중복값 먼저 저장  
		 */
		while(kakao.hasNext()) {
		    String word = (String)kakao.next(); 
		    if(naverList.contains(word)) {
		    	result.add(word);
		    	naverList.remove(word);
		    }
		}
		
		// 중복된 값 처음으로 정렬후 kakao List 값과 중복제거하여 합침
		result = Stream.concat(result.stream(), kakaoList.stream())
						.collect(Collectors.toList()).stream().distinct()
							.collect(Collectors.toList());
		
		// 최종적으로 naver랑 합침
		return  Stream.concat(result.stream(), naverList.stream()).collect(Collectors.toList());
	}
	
	
	
	
	
	/**
	 * 나의 검색 히스토리 저장
	 * 
	 * @methodName : createHistory
	 * @author : cju  
	 * @date : 2021.03.22 
	 * @param area
	 */
	@Transactional
	public void createHistory(String keyword) {
		
		HistoryDto dto = new HistoryDto(LoginManager.getId(), keyword);
		History history =  dto.toEntity();
		historyRepository.save(history);
	}
	
	/**
	 * 검색 내역 등록 및 수정
	 * @methodName : createKeywordHistoty
	 * @author : cju  
	 * @date : 2021.03.22 
	 * @param keyword
	 */
	@Transactional
	public void createKeywordHistoty(String keyword) {
		
		SearchHistory history = searchHistoryRepository.findByKeyword(keyword);
		boolean flag = ObjectUtils.isEmpty(history);
		
		Integer cnt = !flag ? (1 + history.getCnt()) : 1;
		Long id = !flag ? history.getId() : null;
		
		SearchHistoryDto dto = new SearchHistoryDto(cnt, keyword);
		SearchHistory searchHistory =  dto.toEntity();
		searchHistory.setId(id);
		
		searchHistoryRepository.save(searchHistory);
	}
	
	
}
