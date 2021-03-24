package com.mysearch.work.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AreaDto {
	
	@NotBlank(message = "키워드를 입력해주세요.")
	private String keyword;
	
	private String placeName;

	private int currentPage =1;
	private int pageSize = 10;
	
}
