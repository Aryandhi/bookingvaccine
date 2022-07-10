package com.altera.capstone.bookingvaccine.domain.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsernamePassword {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String gender;
    private String email;
    private String noPhone;
    private String roles;
}
