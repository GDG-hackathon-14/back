package com.yanaya.api.member;

import com.yanaya.api.member.dto.MemberLoginReq;
import com.yanaya.api.member.entity.Member;

public interface MemberService {
    Member login(MemberLoginReq memberLoginReq);
}
