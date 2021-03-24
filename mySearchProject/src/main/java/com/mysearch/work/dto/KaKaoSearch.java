package com.mysearch.work.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class KaKaoSearch {
	
	@NotBlank(message = "키워드를 입력해주세요.")
	private String keyword;
	
	private String placeName;

	private int currentPage =1;
	private int pageSize = 10;
	
	private String id;
	private String place_name;
	private String category_name;
	private String category_group_code;
	private String category_group_name;
	private String phone;
	private String address_name;
	private String road_address_name;
	private double x;
	private double y;
	private String place_url;
	private String distance;
}
