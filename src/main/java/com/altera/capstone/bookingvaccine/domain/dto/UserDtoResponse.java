package com.altera.capstone.bookingvaccine.domain.dto;

import com.altera.capstone.bookingvaccine.domain.dao.FamilyDao;
import com.altera.capstone.bookingvaccine.domain.dao.HealthFacilitiesDao;
import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDtoResponse implements Serializable {

  private static final long serialVersionUID = -7087356557915022962L;

  private Long idUser;

  private String username;

  private String password;

  private String firstName;

  private String lastName;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  private String gender;

  private String email;

  private String noPhone;

  private String address;

  private String roles;

//  private String imageName;
//  private String imageType;
  private String imageProfile;

//  private FamilyDao family;
//  private HealthFacilitiesDao healthFacilitiesDaoDtoResponse;
}
