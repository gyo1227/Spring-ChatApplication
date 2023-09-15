package com.chatapplication.server.domain.member.entity;

import com.chatapplication.server.global.constant.MemberStatus;
import com.chatapplication.server.global.constant.ProviderType;
import com.chatapplication.server.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MEMBER")
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Setter
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String tag;

    private String phoneNumber;

    @Column(name = "member_image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type", nullable = false)
    private ProviderType providerType;

    private MemberStatus activated;

    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities;
}
