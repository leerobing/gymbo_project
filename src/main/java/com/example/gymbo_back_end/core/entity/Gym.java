package com.example.gymbo_back_end.core.entity;


import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gym")
@Entity
public class Gym {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gym_seq")
    private Long gymSeq;

    @Column(name = "gym_name")
    private String gymName;

    @Column(name = "gym_address")
    @Embedded
    private Address gymAddress;

    @Column(name = "gym_sports")
    private String gymSports;

    @Column(name = "gym_number") //사업자번호
    private String gymNumber;

    @Column(name = "gym_manager_number")
    private String managerNumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gym_created_at")
    protected Date createdAt;

    @JsonIgnore
    @OneToMany(
            mappedBy = "gym",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<GymPhoto> gymPhoto = new ArrayList<>();


    @Builder
    public Gym( String gymName, Address gymAddress, String gymNumber, String managerNumber,String gymSports) {
        this.gymName = gymName;
        this.gymAddress = gymAddress;
        this.gymSports = gymSports;
        this.gymNumber = gymNumber;
        this.managerNumber = managerNumber;
    }

    public void changeInfo (GymSaveRequestDto gymSaveRequestDto) {
        Address address = new Address(gymSaveRequestDto.getCity()
                ,gymSaveRequestDto.getStreet()
                ,gymSaveRequestDto.getZipCode());

        this.gymName = gymSaveRequestDto.getGymName();
        this.managerNumber = gymSaveRequestDto.getManagerNumber();
        this.gymAddress = address;
        this.gymNumber = gymSaveRequestDto.getGymNumber();
    }

    // Gym에서 파일 처리 위함
    public void addPhoto(GymPhoto gymPhoto) {
        this.gymPhoto.add(gymPhoto);

        // gym에 파일이 저장되어있지 않은 경우
        if(gymPhoto.getGym() != this)
            // 파일 저장
            gymPhoto.setGym(this);
    }
}
