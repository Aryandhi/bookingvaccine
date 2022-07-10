package com.altera.capstone.bookingvaccine.domain.dao;

import com.altera.capstone.bookingvaccine.domain.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "session_vaccine")
@SQLDelete(sql = "UPDATE session_vaccine SET is_deleted = true WHERE id_session = ?")
@Where(clause = "is_deleted = false")
public class SessionDao extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_session;

  @Column(name = "stock", nullable = false)
  private Integer stock;

  @Column(name = "start_date", nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @Column(name = "start_time", nullable = false)
  @DateTimeFormat(pattern = "HH:mm")
  @JsonFormat(pattern = "HH:mm")
  private LocalTime startTime;

  @Column(name = "image_url")
  private String image;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "size")
  private long size;

  @ManyToOne
  @JoinColumn(name = "area_id")
  private AreaDao areaMapped;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sessionMapped")
  private List<BookingDao> bookingDaoList;

  @ManyToOne
  @JoinColumn(name = "vaccine_id",referencedColumnName = "id_vaccine")
  private VaccineDao vaccineMapped;

  @ManyToOne
  @JoinColumn(name = "health_facilities_id")
  private HealthFacilitiesDao healthFacilitiesDaoMapped;

}
