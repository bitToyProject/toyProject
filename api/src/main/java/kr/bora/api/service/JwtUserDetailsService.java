package kr.bora.api.service;

import kr.bora.api.domain.Member;
import kr.bora.api.domain.MemberDto;
import kr.bora.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public Member loadUserByUsername(String username) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException((username)));
    }

    @Transactional
    public Long save(MemberDto memberDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDto.setPassword(encoder.encode(memberDto.getPassword()));

        return memberRepository.save(Member.builder()
                .username(memberDto.getUsername())
                .auth(memberDto.getAuth())
                .password(memberDto.getPassword()).build()).getId();
    }
}
