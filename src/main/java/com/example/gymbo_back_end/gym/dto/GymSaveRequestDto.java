package com.example.gymbo_back_end.gym.dto;

import com.example.gymbo_back_end.core.commom.response.Address;
import lombok.Getter;

import lombok.Setter;




@Getter
@Setter
public class GymSaveRequestDto {


    private String gymName;

    private Address gymAddress;

    private String gymSports;

    private String gymNumber;

    private String managerNumber;

    private String city;

    private String street;

    private String zipCode;

}


