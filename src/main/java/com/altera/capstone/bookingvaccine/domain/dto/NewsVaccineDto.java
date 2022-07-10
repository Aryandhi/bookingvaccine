package com.altera.capstone.bookingvaccine.domain.dto;

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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NewsVaccineDto {

    private Long id_news_vaccine;

    @ApiModelProperty(notes = "Judul berita", example = "Pemberitahuan tentang kesehatan")
    private String titleNewsVaccine;

    @ApiModelProperty(notes = "Author berita", example = "Admin")
    private String authorNewsVaccine;

    @ApiModelProperty(notes = "Isi berita", example = "Pemberitahuan tentang kesehatan")
    private String contentNewsVaccine;

    @ApiModelProperty(notes = "response after success upload", example = "images/random string")
    private String image;

    @ApiModelProperty(notes = "response after success upload", example = "filename.png")
    private String fileName;

    @ApiModelProperty(notes = "response after success upload", example = "size image")
    private long size;
}
