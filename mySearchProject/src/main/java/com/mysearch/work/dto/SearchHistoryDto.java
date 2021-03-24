package com.mysearch.work.dto;

import com.mysearch.work.entity.SearchHistory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchHistoryDto {

	private Integer cnt;
	private String keyword;
	
	@Builder
    public SearchHistoryDto(Integer cnt, String keyword) {
		this.cnt = cnt;
		this.keyword = keyword;
    }
	

	public SearchHistory toEntity(){
        return SearchHistory.builder()
                .keyword(keyword)
                .cnt(cnt)
                .build();
    }

}
