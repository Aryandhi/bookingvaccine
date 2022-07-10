package com.altera.capstone.bookingvaccine.domain.dao;

import com.altera.capstone.bookingvaccine.domain.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
@SQLDelete(sql = "UPDATE booking SET is_deleted = true WHERE id_booking = ?")
@Where(clause = "is_deleted = false")
public class BookingDao extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_booking;

  @ManyToOne
  @JoinColumn(name = "id_user")
  private UserDao userMapped;

  @ManyToOne
  @JoinColumn(name = "family_id")
  private FamilyDao familyMapped ;

//  @ManyToMany
//  private Set<FamilyDao> familyDaoSet;

  @ManyToOne
  @JoinColumn(name = "session_id")
  private SessionDao sessionMapped;



}
