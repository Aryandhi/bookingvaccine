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
@Table(name = "category_facilities")
public class CategoryFacilitiesDao extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_category_facilities;

  @Column(name = "category_facilities_name", nullable = false)
  private String categoryFacilitiesName;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categoryMapped")
  private List<HealthFacilitiesDao> healthFacilitiesDaoList;
}
