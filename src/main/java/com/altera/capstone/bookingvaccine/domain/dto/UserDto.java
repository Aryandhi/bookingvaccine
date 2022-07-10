package com.altera.capstone.bookingvaccine.domain.dto;

import com.altera.capstone.bookingvaccine.domain.dao.FamilyDao;
import com.altera.capstone.bookingvaccine.domain.dao.HealthFacilitiesDao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
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
//implements Serializable
public class UserDto implements Serializable{

  private static final long serialVersionUID = -5607905544859605735L;

//  private Long idUser;

  @ApiModelProperty(notes = "Please Insert NIK for user", example = "1871xxxxxxxxxxxx")
  private String username;

  @ApiModelProperty(notes = "Password", example = "Your Password")
  private String password;

  @ApiModelProperty(notes = "First Name", example = "Your First Name")
  private String firstName;

  @ApiModelProperty(notes = "Last Name", example = "Your Last Name")
  private String lastName;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  @ApiModelProperty(notes = "Gender", example = "Laki-laki atau Perempuan")
  private String gender;

  @ApiModelProperty(notes = "E-mail", example = "test@test.com")
  private String email;

  @ApiModelProperty(notes = "Phone", example = "08124445554444")
  private String noPhone;

  @ApiModelProperty(notes = "Just Address Admin", example = "Dragon street")
  private String address;

  @ApiModelProperty(notes = "Role", example = "USER or Admin")
  private String roles;
}
