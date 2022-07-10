package com.altera.capstone.bookingvaccine.domain.dao;

import com.altera.capstone.bookingvaccine.domain.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "area")
public class AreaDao extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_area;

  @Column(name = "area", nullable = false, unique = true)
  private String areaName;

//  @JsonIgnore
//  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "areaMapped")
//  private List<SessionDao> sessionDaoList;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "areaMapped")
  private List<HealthFacilitiesDao> healthFacilitiesDaoList;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "areaMapped")
  private List<SessionDao> sessionDaoList;
}
