package com.altera.capstone.bookingvaccine.domain.dto;

import com.altera.capstone.bookingvaccine.domain.dao.AreaDao;
import com.altera.capstone.bookingvaccine.domain.dao.HealthFacilitiesDao;
import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.altera.capstone.bookingvaccine.domain.dao.VaccineDao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SessionDtoResponse {

  private Long id_session;

  private Integer stock;

  @ApiModelProperty(notes = "start on session Date", example = "01-01-2001")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @ApiModelProperty(notes = "start on session", example = "08:00:00")
  @DateTimeFormat(pattern = "HH:mm")
  @JsonFormat(pattern = "HH:mm")
  private LocalTime startTime;

  private AreaDao areaDao;

  private VaccineDao vaccineDao;

  private HealthFacilitiesDao healthFacilitiesDao;
}
