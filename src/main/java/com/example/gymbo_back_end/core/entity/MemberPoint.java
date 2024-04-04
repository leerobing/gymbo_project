package com.example.gymbo_back_end.core.entity;

import com.example.gymbo_back_end.member.dto.MemberPointRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member_point")
@Getter
@NoArgsConstructor
public class MemberPoint {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberPointSeq;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "member_seq")
    private Member member;

    @Column(name = "point")
    private Long point;

    @Builder
    public MemberPoint(Member member, Long point) {
        this.member = member;
        this.point = point;
    }

    public void changePoint( Long point) {
        this.point = point;
    }
}
