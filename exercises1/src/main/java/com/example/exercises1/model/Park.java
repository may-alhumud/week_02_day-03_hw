package com.example.exercises1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor @Data
public class Park {
    @NotNull(message = "id is required")
    @Length(min =3,message = " id must be more than 2 number ")
    private String rideID;
    @NotNull(message = " name  is required")
    @Length(min =5,message = " id must be more than 4 number ")
    private String rideName;
    @NotNull(message = " type  is required")
    @Pattern(regexp= "Rollercoaster|thriller|water")
    private String rideType;
    @NotNull(message = " tickets  is required")
    @Digits(integer =10,fraction = 0)
    private long tickets;
    @NotNull(message = " price is required")
    @Digits(integer =10,fraction = 0)
    private long price;

}
