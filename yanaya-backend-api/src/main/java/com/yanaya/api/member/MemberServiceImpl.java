package com.yanaya.api.member;

import com.yanaya.api.member.dto.MemberLoginReq;
import com.yanaya.api.member.entity.Member;
import com.yanaya.api.member.repository.MemberRepository;
import com.yanaya.api.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    public MemberServiceImpl(@Autowired MemberRepository memberRepository,
                             @Autowired ProfileRepository profileRepository) {
        this.memberRepository = memberRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Member login(MemberLoginReq memberLoginReq) {
        Optional<Member> findMember = memberRepository.findByEmail(memberLoginReq.getEmail());

        if (findMember.isEmpty()) {
            Member memberEntity = Member.builder()
                    .email(memberLoginReq.getEmail())
                    .password(memberLoginReq.getPassword())
                    .build();
            memberRepository.save(memberEntity);
            return memberEntity;
        }

        if(!findMember.get().getPassword().equals(memberLoginReq.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return findMember.get();
    }

}
