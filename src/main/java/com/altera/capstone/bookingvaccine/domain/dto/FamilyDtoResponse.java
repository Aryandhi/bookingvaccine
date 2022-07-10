package com.altera.capstone.bookingvaccine.domain.dto;

import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FamilyDtoResponse implements Serializable {

  private static final long serialVersionUID = -7087356557915022962L;

  private Long id_family;

  private String nik;

  private String fullName;

  private UserDao user;
}
