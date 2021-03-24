package com.mysearch.work.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysearch.work.dto.MemberDto;
import com.mysearch.work.entity.Member;
import com.mysearch.work.repository.MemberRepository;
import com.mysearch.work.service.MemberJoinService;
import com.mysearch.work.vaild.VaildService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberJoinController {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberJoinService memberJoinService;
	@Autowired
	private VaildService memberJoinVaild;

	/**
	 * 회원가입 화면
	 * @return
	 */
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	
	/**
	 * 회원가입
	* @methodName : joinCreate
	* @author : cju  
	* @date : 2021.03.21 
	* @param memberDto
	* @param result
	* @return
	* @throws Exception
	 */
	@PostMapping("/sign/up")
	public String joinCreate(@Valid MemberDto memberDto, Errors errors, Model model) throws Exception {
		
		// 중복체크
		if(!ObjectUtils.isEmpty(memberRepository.findByUsername(memberDto.getUsername()))) {
			 model.addAttribute("valid_username", "이미 존재하는 아이디입니다.");
			 return "member/join";
		}
		
		if (errors.hasErrors() ) {
			
           // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("userDto", memberDto);
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = memberJoinVaild.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

	        return "member/join";
	     }
		
		try {
			memberJoinService.memberJoin(memberDto);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return "redirect:/login/form";
	}

}
