package com.yanaya.api.profile;

import com.yanaya.api.profile.dto.ProfileDto;
import com.yanaya.api.profile.dto.ProfileReq;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    ProfileDto createProfile(Long memberId, ProfileReq profileReq, MultipartFile profileImageFile);

    ProfileDto readProfile(String memberCustomUrl);
}
