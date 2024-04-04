package com.example.gymbo_back_end.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_seq")
    private Long reservationSeq;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_seq")
    private Gym gym;

    @Column(name = "start_day")
    private String startDay;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_date")
    private Date reservationDate;                // 예약 생성 날짜


    public static Reservation createdReservation(String startTime, String endTime,String startDay, Gym gym) {

        Reservation reservation = Reservation.builder()
                .gym(gym)
                .startDay(startDay)
                .startTime(startTime)
                .endTime(endTime).build();
        return reservation;

    }


}
