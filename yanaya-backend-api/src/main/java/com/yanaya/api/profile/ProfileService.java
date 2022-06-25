package com.yanaya.api.profile;

import com.yanaya.api.profile.dto.ProfileDto;
import com.yanaya.api.profile.dto.ProfileReq;
import com.yanaya.api.profile.dto.ProfileUrlDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    ProfileUrlDto createProfile(Long memberId, ProfileReq profileReq, MultipartFile profileImageFile);

    ProfileDto readProfile(String memberCustomUrl);

    ProfileDto readProfile(Long memberId);
}
