package com.mysearch.work.member;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;


public class LoginManager {


    /**
     * 스프링시큐리티 세션의 사용자 정보 저장
     * ※주의. 해당 로직은 사용을 권장하지 않습니다. 정말 필요한 곳만 사용하세요.
     *
     * @param MemberDetail
     */
    public static void setMemberDetail(MemberDetail MemberDetail) {
        if (MemberDetail == null || SecurityContextHolder.getContext() == null) {
            return;
        }

        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(MemberDetail.getUsername(), null, MemberDetail.getAuthorities());
        token.setDetails(MemberDetail);
        token.eraseCredentials();

        SecurityContextHolder.getContext().setAuthentication(token);
    }

    /**
     * 사용자 정보 조회
     *
     * @return MemberDetail
     */
    public static MemberDetail getMemberDetail() {
        Object details = getDetails();
        if (details == null || !(details instanceof MemberDetail))
            return null;

        return (MemberDetail) details;
    }

    /**
     * 사용자 고유 번호 조회
     *
     * @return ID (고유번호)
     */
    public static Long getId() {
        return getMemberDetailId();
    }
    
    public static boolean isLogin() {
    	return !ObjectUtils.isEmpty(getAuthentication());
    }

    /**
     * 회원 식별번호 조회
     *
     * @return
     */
    private static Long getMemberDetailId() {
        MemberDetail MemberDetail = getMemberDetail();
        if (MemberDetail == null)
            return null;

        return MemberDetail.getMbrId();
    }

    /**
     * 사용자 로그인 ID 조회
     *
     * @return 로그인 ID
     */
    public static String getLoginId() {
        MemberDetail MemberDetail = getMemberDetail();
        if (MemberDetail == null)
            return null;

        return MemberDetail.getUsername();
    }
    
    public static Object getDetails() {
        Authentication authentication = getAuthentication();
        if (authentication == null)
            return null;

        return authentication.getPrincipal();
    }
    
    
    public static Authentication getAuthentication() {
        SecurityContext context = null;
        try {
            context = SecurityContextHolder.getContext();
        }
        catch (Exception e) {

        }
        if (context == null)
            return null;

        Authentication authentication = context.getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken)
            return null;

        return authentication;
    }


}


