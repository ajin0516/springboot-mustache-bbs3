package com.springbootmustache.bbs3.service;

import com.springbootmustache.bbs3.Exception.ErrorCode;
import com.springbootmustache.bbs3.Exception.HospitalReviewAppException;
import com.springbootmustache.bbs3.domain.User;
import com.springbootmustache.bbs3.domain.dto.UserDto;
import com.springbootmustache.bbs3.domain.dto.UserJoinRequest;
import com.springbootmustache.bbs3.repository.UserRepository;
import com.springbootmustache.bbs3.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository; // findByUserName
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60; // 1시간

    public UserDto join(UserJoinRequest request) {
        // 비지니스 로직 - 회원가입

        // 회원 userName(id) 중복 check
        // 중복이면 회원가입(x) -> Exception 발생
        // 있으면 에러처리
//        userRepository.findByUserName(request.getUserName())
//                .ifPresent((user) -> { throw new RuntimeException("해당 UserName이 중복됩니다");});

        userRepository.findByUserName(request.getUserName())
                .ifPresent((user) -> { throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, request.getUserName() + "은 중복입니다");});


        // 회원가입 save
        // request -> entity 변환
        User saveUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .id(saveUser.getId())
                .userName(saveUser.getUserName())
                .email(saveUser.getEmailAddress())
                .build();
    }

    public String login(String userName, String password) {

        // userName 있는지 확인
        User user = userRepository.findByUserName(userName)
                .orElseThrow(
                        () -> new HospitalReviewAppException(ErrorCode.NOT_FOUND, String.format("%s 은 잘못된 userName입니다",userName)));
        // password 일치 여부
        if(!(encoder.matches(password ,user.getPassword()))){
            throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD, String.format("userName 또는 password가 잘못됐습니다."));
        }
        // 위 두개 확인 후 token 발행
        log.info("secretKey={}",secretKey);
        log.info("userName={}",userName);

        return JwtTokenUtil.createToken(userName,secretKey ,expireTimeMs);
    }
}
