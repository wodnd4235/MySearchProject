package com.mysearch.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.mysearch.work.dto.MemberDto;
import com.mysearch.work.entity.Member;
import com.mysearch.work.repository.MemberRepository;

@Service
public class MemberJoinService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * 회원 가입 서비스 
	 * @param member
	 */
	public void memberJoin(MemberDto memberDto) throws Exception {
		
		Member member = memberDto.toEntity();
		member.setRole("ROLE_ADMIN");
		this.memberRepository.save(member);
	    
	}

}
