package com.mysearch.work.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SEARCH_HISTORY")
@NoArgsConstructor 
@AllArgsConstructor
@DynamicUpdate
public class SearchHistory  extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "KEYWORD", length = 500)
	private String keyword;
	
	@Column(name = "CNT")
	private Integer cnt;
	
//	@CreatedDate
//	private Date  regDt;
//	
//	@LastModifiedDate
//	private Date  modDt;

	
	@Builder
    public SearchHistory( String keyword, Integer cnt) {
		this.keyword = keyword;
		this.cnt = cnt;
    
    }
	
}
