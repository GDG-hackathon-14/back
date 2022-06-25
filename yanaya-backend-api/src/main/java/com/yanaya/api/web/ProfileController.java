package com.yanaya.api.web;

import com.yanaya.api.profile.ProfileService;
import com.yanaya.api.profile.dto.ProfileDto;
import com.yanaya.api.profile.dto.ProfileReq;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("{memberId}")
    public ResponseEntity<ProfileDto> createProfile(
            @PathVariable Long memberId,
            @Valid @RequestPart(value = "profileReq") ProfileReq profileReq,
            @RequestPart(value = "profileImageFile", required = false) MultipartFile profileImageFile) {
        return new ResponseEntity<>(profileService.createProfile(memberId, profileReq, profileImageFile), HttpStatus.CREATED);
    }

    @GetMapping("/{memberCustomUrl}")
    public ResponseEntity<ProfileDto> readProfile(@PathVariable String memberCustomUrl) {
        return new ResponseEntity<>(profileService.readProfile(memberCustomUrl), HttpStatus.OK);
    }
}
