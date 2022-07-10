package com.altera.capstone.bookingvaccine.domain.dao;

import com.altera.capstone.bookingvaccine.domain.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "health_facilities")
@SQLDelete(sql = "UPDATE health_facilities SET is_deleted = true WHERE id_health_facilities = ?")
@Where(clause = "is_deleted = false")
public class HealthFacilitiesDao extends BaseEntity{

  private static final long serialVersionUID = -1266576651734156259L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_health_facilities;

  @Column(name = "health_facilities_name", nullable = false, unique = true)
  private String healthFacilitiesName;

  @Column(name = "address_health_facilities", nullable = false, unique = true)
  private String addressHealthFacilities;

  @Column(name = "link_location", nullable = false, unique = true)
  private String linkLocation;

  @Column(name = "phone_facilities", nullable = false, unique = true)
  private String phoneFacilities;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_user")
  private UserDao userMapped;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "healthFacilitiesDaoMapped")
  private List<SessionDao> sessionDaoList;

  @ManyToOne
  @JoinColumn(name = "id_category_facilities")
  private CategoryFacilitiesDao categoryMapped;

  @ManyToOne
  @JoinColumn(name = "id_area")
  private AreaDao areaMapped;
}
