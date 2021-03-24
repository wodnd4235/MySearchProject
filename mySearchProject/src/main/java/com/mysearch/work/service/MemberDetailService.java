package com.mysearch.work.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysearch.work.entity.Member;
import com.mysearch.work.member.MemberDetail;
import com.mysearch.work.repository.MemberRepository;

@Service
public class MemberDetailService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		Optional<Member> optionalUser = memberRepository.findByUsername(username);
		return optionalUser.map(MemberDetail::new).orElse(null);
	}


}
