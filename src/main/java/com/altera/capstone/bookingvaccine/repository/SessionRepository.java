package com.altera.capstone.bookingvaccine.repository;

import com.altera.capstone.bookingvaccine.domain.dao.HealthFacilitiesDao;
import com.altera.capstone.bookingvaccine.domain.dao.SessionDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<SessionDao, Long> {
//  @Query(value = "SELECT * FROM health_facilities u WHERE u.id_user = 1", nativeQuery = true)
  @Query(value = "SELECT * FROM session_vaccine s WHERE s.area_id = :area_id AND is_deleted = false", nativeQuery = true)
  List<SessionDao> getSessionByAreaId(@PathVariable("area_id")Long area_id);

  // searching with LIKE
  @Query("SELECT s FROM SessionDao s WHERE s.healthFacilitiesDaoMapped.healthFacilitiesName " +
          "LIKE %?1% OR s.vaccineMapped.vaccineName LIKE %?2%")
  List<SessionDao> findByFacilityLike(String health_facility_name, String vaccine_name);
}
