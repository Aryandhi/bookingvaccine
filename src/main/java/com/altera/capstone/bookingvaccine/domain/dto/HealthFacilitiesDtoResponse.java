package com.altera.capstone.bookingvaccine.domain.dto;

import com.altera.capstone.bookingvaccine.domain.dao.AreaDao;
import com.altera.capstone.bookingvaccine.domain.dao.CategoryFacilitiesDao;
import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthFacilitiesDtoResponse {
  private Long id_health_facilities;

  private String healthFacilitiesName;

  private String addressHealthFacilities;

  private String linkLocation;

  private String phoneFacilities;

  private UserDto user;

  private AreaDto area;

  private CategoryFacilitiesDto categoryFacilities;
}
