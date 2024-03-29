package com.chatapplication.server.domain.member.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "AUTHORITY")
@Entity
@ToString
public class Authority {

    @Id
    @Column(name = "authority_name")
    private String authority;
}
