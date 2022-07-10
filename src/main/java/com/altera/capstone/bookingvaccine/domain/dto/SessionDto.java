package com.altera.capstone.bookingvaccine.domain.dto;

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

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SessionDto {

  private Long id_session;

  @ApiModelProperty(notes = "input existing stock", example = "100")
  private Integer stock;

  @ApiModelProperty(notes = "start on session Date", example = "01-01-2001")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @ApiModelProperty(notes = "start on session", example = "08.00")
  @DateTimeFormat(pattern = "HH:mm")
  @JsonFormat(pattern = "HH:mm")
  private LocalTime startTime;

  @ApiModelProperty(notes = "response after success upload", example = "images/random string")
  @Column(name = "image_url")
  private String image;

  @ApiModelProperty(notes = "response after success upload", example = "filename.png")
  @Column(name = "file_name")
  private String fileName;

  @ApiModelProperty(notes = "response after success upload", example = "size image")
  @Column(name = "size")
  private long size;

//  @ApiModelProperty(notes = "session ended", example = "10.00")
//  @DateTimeFormat(pattern = "HH:mm:ss")
//  @JsonFormat(pattern = "HH:mm:ss")
//  private LocalTime endTime;

//  @ApiModelProperty(notes = "remaining vaccine stock", example = "50")
//  private Integer lastStock;

  private Long idArea;

  private Long idVaccine;

  private Long idHealthFacilities;
}
