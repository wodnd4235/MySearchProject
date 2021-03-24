package com.mysearch.work.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mysearch.work.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {
	
	@NotBlank(message = "아이디를 입력해주세요.")
//    @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
//    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,12}", message = "비밀번호는 영문자와 숫자, 특수기호가 적어도 1개 이상 포함된 6자~12자의 비밀번호여야 합니다.")
    private String password;
    
    private String role;    
	
    
    @Builder
    public MemberDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Member toEntity(){
        return Member.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .role(role)
                .build();
    }
}
