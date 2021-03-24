package com.mysearch.work;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.mysearch.work.entity.Member;
import com.mysearch.work.repository.MemberRepository;
	

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
class MemberTests {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void joinMemberTest() {
		Member member = new Member();
		member.setUsername("andrew");
		memberRepository.save(member);
		
		Member retrivedMember = memberRepository.findById(member.getMbrId()).get();
		
		// then 삭제
		Assert.assertEquals(retrivedMember.getUsername(), "andrew");
	}

}
