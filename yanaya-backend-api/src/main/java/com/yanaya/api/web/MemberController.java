package com.yanaya.api.web;

import com.yanaya.api.member.MemberService;
import com.yanaya.api.member.dto.MemberLoginDto;
import com.yanaya.api.member.dto.MemberLoginReq;
import com.yanaya.api.member.entity.Member;
import com.yanaya.api.profile.ProfileService;
import com.yanaya.api.profile.dto.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final ProfileService profileService;

    public MemberController(@Autowired MemberService memberService,
                            @Autowired ProfileService profileService) {
        this.memberService = memberService;
        this.profileService = profileService;
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginDto> login(@Valid @RequestBody MemberLoginReq memberLoginReq, HttpServletRequest request) {
        System.out.println("memberLoginReq = " + memberLoginReq.toString());
        Member loginMember = memberService.login(memberLoginReq);
        // 로그인 성공처리
        // 세션이 있으면 세션 반환 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute("memberId", loginMember.getMemberId());

//        ProfileDto profileDto = profileService.readProfile(loginMember.getMemberId());
        return new ResponseEntity<>(MemberLoginDto.builder()
                .memberId(loginMember.getMemberId())
                .build(), HttpStatus.OK);

    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ProfileDto> myProfile(@PathVariable Long memberId) {
        return new ResponseEntity<>(profileService.readProfile(memberId), HttpStatus.OK);
    }
}
