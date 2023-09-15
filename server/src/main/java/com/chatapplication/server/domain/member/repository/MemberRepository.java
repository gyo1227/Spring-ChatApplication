package com.chatapplication.server.domain.member.repository;

import com.chatapplication.server.domain.member.entity.Member;
import com.chatapplication.server.global.constant.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndActivated(String email, MemberStatus activated);
}
