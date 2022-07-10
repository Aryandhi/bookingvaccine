package com.altera.capstone.bookingvaccine.domain.dto;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FamilyDto {

    private static final long serialVersionUID = -5607905544859605735L;

    private Long id_family;

    @ApiModelProperty(notes = "kamu harus isi NIK", example = "1871xxxxxxxxxxxx")
    private String nik;

    @ApiModelProperty(notes = "dan mengisi nama lengkap", example = "NAMA SAYA")
    private String fullName;

    private Long idUser;
}
