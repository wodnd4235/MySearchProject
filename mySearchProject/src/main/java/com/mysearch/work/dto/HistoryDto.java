package com.mysearch.work.dto;

import com.mysearch.work.entity.History;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryDto {

	private Long mbrId;
	
	private String keyword;
	
	
	@Builder
    public HistoryDto(Long mbrId, String keyword) {
		this.mbrId = mbrId;
		this.keyword = keyword;
    }
	

	public History toEntity(){
        return History.builder()
                .keyword(keyword)
                .mbrId(mbrId)
                .build();
    }

}
