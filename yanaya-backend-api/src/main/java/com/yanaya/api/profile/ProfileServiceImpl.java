package com.yanaya.api.profile;

import com.yanaya.api.company.entity.Company;
import com.yanaya.api.company.repository.CompanyRepository;
import com.yanaya.api.profile.dto.ProfileDto;
import com.yanaya.api.profile.dto.ProfileReq;
import com.yanaya.api.profile.dto.ProfileUrlDto;
import com.yanaya.api.profile.entity.Profile;
import com.yanaya.api.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
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
    public ProfileUrlDto createProfile(Long memberId, ProfileReq profileReq, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename(); // 1.jpg
        System.out.println("이미지 파일이름 : " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                .techSkill(profileReq.getTechSkill())
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
        Profile profileEntity = profileRepository.findByMemberCustomUrl(memberCustomUrl).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND));

        Company companyEntity = companyRepository.findByCompId(profileEntity.getCompId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return getProfileDto(profileEntity, companyEntity);
    }

    @Override
    public ProfileDto readProfile(Long memberId) {
        Profile profileEntity = profileRepository.findById(memberId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        Company companyEntity = companyRepository.findByCompId(profileEntity.getCompId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
                .techSkill(profileEntity.getTechSkill())
                .mbti(profileEntity.getMbti())
                .links(profileEntity.getLinks())
                .description(profileEntity.getDescription())
                .memberCustomUrl(profileEntity.getMemberCustomUrl())
                .build();
    }
}
