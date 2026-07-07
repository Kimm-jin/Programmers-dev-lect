package org.example.springboot.report_36_회원가입.domain.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =  50)
    private String userId;

    @Column(nullable = false, length =  50)
    private String password;

    @Column(nullable = false, length =  20)
    private String userName;

}
