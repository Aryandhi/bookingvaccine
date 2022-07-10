package com.altera.capstone.bookingvaccine.domain.dto;

import com.altera.capstone.bookingvaccine.domain.dao.FamilyDao;
import com.altera.capstone.bookingvaccine.domain.dao.SessionDao;
import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookingDtoResponse {
  private Long id_booking;
  private UserDao user;
  private FamilyDao family;
  private SessionDao session;
}
