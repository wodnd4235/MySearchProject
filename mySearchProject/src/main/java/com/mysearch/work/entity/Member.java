package com.mysearch.work.entity;

import java.util.Date;

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
@Table(name = "MEMBER")
@NoArgsConstructor 
@AllArgsConstructor
public class Member  extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mbrId;
	
	@Column(name = "USERNAME", length = 100)
	private String username;
	
	@Column(name = "PASSWORD", length = 200)
	private String password;
	
	@Column(name = "ROLE", length = 20)
	private String role;
	

	
	@Builder
    public Member(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
	
}
