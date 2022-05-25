package com.example.exercises2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Employees {
    @NotNull(message = "id is required")
    @Length(min =3,message = " id must be more than 2 number ")
    private String ID;
    @NotNull(message = " name  is required")
    @Length(min =5,message = " id must be more than 4 number ")
    private String Name;
    @NotNull(message = " age  is required")
    @Digits(integer =2,fraction = 0)
    @Min(value =26,message = "You must be older than 25")
    private String age;
    @AssertFalse(message = "always false")
    private Boolean onLeave;
    @NotNull(message = " EmploymentYear is required")
    @Digits(integer =10,fraction = 0)
    @Future(message = "must be a valid year")
    private String employmentYear;
    @NotNull(message = " annualLeave is required")
    @Digits(integer =10,fraction = 0)
    private String annualLeave;


}
