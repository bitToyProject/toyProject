package kr.bora.api.userteam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserTeamServiceImpl implements UserTeamService{

    private final UserTeamRepository repository;

}
