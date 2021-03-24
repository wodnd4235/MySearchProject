package com.mysearch.work.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "HISTORY")
@NoArgsConstructor 
@AllArgsConstructor
public class History  extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	@Column(name = "MBR_ID", length = 500)
	private Long mbrId;
	
	@Column(name = "KEYWORD", length = 500)
	private String keyword;
	

	
	@Builder
    public History(Long mbrId, String keyword) {
		this.mbrId = mbrId;
		this.keyword = keyword;
    }
	
}
