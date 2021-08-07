package kr.bora.api.service;

import kr.bora.api.domain.Member;
import kr.bora.api.domain.MemberDto;
import kr.bora.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(Member member) {
        Optional<Member> alreadyMember = memberRepository.findByUsername(member.getUsername());
        if (alreadyMember.isPresent()) {
            throw new IllegalArgumentException ("username duplicated");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username) {
        Optional<Member> alreadyMember = memberRepository.findByUsername(username);
        return alreadyMember;
    }
}


