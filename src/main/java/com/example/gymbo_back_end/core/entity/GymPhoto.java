package com.example.gymbo_back_end.core.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "gym_photo")
public class GymPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_seq")
    private Long photoSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_seq")
    private Gym gym;

    @Column(nullable = false)
    private String origFileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    @Builder
    public GymPhoto(String origFileName, String filePath, Long fileSize){
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // gym 정보 저장
    public void setGym(Gym gym){
        this.gym = gym;
        // gym에 현재 파일이 존재하지 않는다면
        if(!gym.getGymPhoto().contains(this))
            // 파일 추가
            gym.getGymPhoto().add(this);
    }
}