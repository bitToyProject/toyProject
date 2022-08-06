package kr.bora.api.user.util;

import kr.bora.api.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecurityUtil {

    // static메서드에 repository 주입 시
    // 방법 1 -> setter 메서드를 사용
    // 방법 2 -> 생성자를 이용
    public static UserRepository userRepository;

    @Autowired
    private SecurityUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Autowired
//    private void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장

    public static Long getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int socialIdLength = authentication.getName().getBytes().length;

        if (socialIdLength == 21 || socialIdLength== 10 || socialIdLength == 43) {

            Long findSocialUserId = userRepository.findUserId(authentication.getName());

            // 구글 로그인시 -> subId가 pk(21자리)
            if (socialIdLength == 21) {
                Long googleUserId = findSocialUserId;
                return googleUserId;
            }

            //카카오 로그인시 -> 고유 id 10자리
            if (socialIdLength == 10) {
                Long kakaoUserId = findSocialUserId;
                return kakaoUserId;
            }

            //네이버 로그인시 -> 고유 id 43자리
            if (socialIdLength == 43) {
                Long naverUserId = findSocialUserId;
                return naverUserId;
            }
        }

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }
        //일반 유저 로그인
        return Long.parseLong(authentication.getName());
    }

}