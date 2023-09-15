package com.chatapplication.server.domain.member.repository;

import com.chatapplication.server.domain.member.entity.Member;
import com.chatapplication.server.global.constant.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndProviderType(String email, ProviderType providerType);
    Optional<Member> findByPhoneNumberAndProviderType(String phoneNumber, ProviderType providerType);
    Optional<Member> findByNicknameAndTag(String nickname, String tag);
}
