package com.altera.capstone.bookingvaccine.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HealthFaciltiesDto {

  private Long id_health_facilities;

  @ApiModelProperty(notes = "PUSKESMAS atau RSUD", example = "PUSKESMAS atau RSUD")
  private String healthFacilitiesName;

  @ApiModelProperty(notes = "nama Jalan puskesmas", example = "JL Raya, No, Kecamatan")
  private String addressHealthFacilities;

  @ApiModelProperty(notes = "url lokasi", example = "https://goo.gl/maps/YQH25RZHMmqgsQGF8")
  private String linkLocation;

  @ApiModelProperty(notes = "(kode area) no phone", example = "(021) 554422 ")
  private String phoneFacilities;

  private Long idUser;

  private Long idArea;

  private Long idCategoryFacilities;

}
