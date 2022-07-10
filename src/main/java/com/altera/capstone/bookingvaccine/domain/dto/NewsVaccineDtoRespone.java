package com.altera.capstone.bookingvaccine.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * NewsVaccineDtoRespone
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NewsVaccineDtoRespone {
    private Long id_news_vaccine;

    private String titleNewsVaccine;

    private String authorNewsVaccine;

    private String contentNewsVaccine;

    private String image;

    private String fileName;

    private long size;
}