package com.yanaya.api.profile;

import com.yanaya.api.company.entity.Company;
import com.yanaya.api.company.repository.CompanyRepository;
import com.yanaya.api.profile.dto.ProfileDto;
import com.yanaya.api.profile.dto.ProfileReq;
import com.yanaya.api.profile.dto.ProfileUrlDto;
import com.yanaya.api.profile.entity.Profile;
import com.yanaya.api.profile.repository.ProfileRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final CompanyRepository companyRepository;

    public ProfileServiceImpl(
            @Autowired ProfileRepository profileRepository,
            @Autowired CompanyRepository companyRepository) {
        this.profileRepository = profileRepository;
        this.companyRepository = companyRepository;
    }

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    @Override
    public ProfileUrlDto createProfile(Long memberId, ProfileReq profileReq) throws IOException {
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid + "_" + profileReq.getProfileImageName();
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        byte[] decode = Base64.decodeBase64(profileReq.getProfileImageUrl());
        FileOutputStream fos;
        File target = new File(imageFilePath.toString());
        target.createNewFile();
        fos = new FileOutputStream(target);
        fos.write(decode);
        fos.close();

        String memberCustomUrl = uuid + memberId.toString();

        Company companyEntity = companyRepository.findByCompId(profileReq.getCompId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Profile profileEntity = Profile.builder()
                .compId(profileReq.getCompId())
                .profileImageUrl(imageFileName)
                .memberName(profileReq.getMemberName())
                .email(profileReq.getEmail())
                .phoneNumber(profileReq.getPhoneNumber())
                .gender(profileReq.getGender())
                .hobby(profileReq.getHobby())
                .deptName(profileReq.getDeptName())
                .address(profileReq.getAddress())
                .task(profileReq.getTask())
                .position(profileReq.getPosition())
                .techSkills(profileReq.getTechSkills())
                .mbti(profileReq.getMbti())
                .links(profileReq.getLinks())
                .description(profileReq.getDescription())
                .memberCustomUrl(memberCustomUrl)
                .memberId(memberId)
                .build();

        profileEntity = profileRepository.save(profileEntity);
        return ProfileUrlDto.builder()
                .memberCustomUrl(profileEntity.getMemberCustomUrl())
                .build();
    }

    @Override
    public ProfileDto readProfile(String memberCustomUrl) {
        Profile profileEntity = profileRepository.findByMemberCustomUrl(memberCustomUrl).get();
//        Profile profileEntity = profileRepository.findByMemberCustomUrl(memberCustomUrl).orElseThrow(() ->
//        new ResponseStatusException(HttpStatus.NOT_FOUND));
        Company companyEntity = companyRepository.findByCompId(profileEntity.getCompId()).get();

//        Company companyEntity = companyRepository.findByCompId(profileEntity.getCompId()).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return getProfileDto(profileEntity, companyEntity);
    }

    @Override
    public ProfileDto readProfile(Long memberId) {
        Profile profileEntity = profileRepository.findById(memberId).get();
//        Profile profileEntity = profileRepository.findById(memberId).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND));

        Company companyEntity = companyRepository.findByCompId(profileEntity.getCompId()).get();

//        Company companyEntity = companyRepository.findByCompId(profileEntity.getCompId()).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return getProfileDto(profileEntity, companyEntity);
    }

    private ProfileDto getProfileDto(Profile profileEntity, Company companyEntity) {
        return ProfileDto.builder()
                .compId(profileEntity.getCompId())
                .profileImageUrl(profileEntity.getProfileImageUrl())
                .memberName(profileEntity.getMemberName())
                .email(profileEntity.getEmail())
                .phoneNumber(profileEntity.getPhoneNumber())
                .gender(profileEntity.getGender())
                .hobby(profileEntity.getHobby())
                .address(profileEntity.getAddress())
                .deptName(profileEntity.getDeptName())
                .task(profileEntity.getTask())
                .position(profileEntity.getPosition())
                .techSkills(profileEntity.getTechSkills())
                .mbti(profileEntity.getMbti())
                .links(profileEntity.getLinks())
                .description(profileEntity.getDescription())
                .memberCustomUrl(profileEntity.getMemberCustomUrl())
                .build();
    }
}
