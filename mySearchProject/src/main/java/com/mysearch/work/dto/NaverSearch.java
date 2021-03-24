package com.mysearch.work.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NaverSearch {
	
	@NotBlank(message = "키워드를 입력해주세요.")
	private String keyword;
	
	private String placeName;

	private int currentPage =1;
	private int pageSize = 10;
	
	private String title;
	private String link;
	private String category;
	private String description;
	private String telephone; 
	private String address;
	private String roadAddress;
	private String mapx;
	private String mapy;
}
