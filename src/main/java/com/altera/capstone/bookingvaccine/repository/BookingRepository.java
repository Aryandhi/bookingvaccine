package com.altera.capstone.bookingvaccine.repository;

import com.altera.capstone.bookingvaccine.domain.dao.BookingDao;
import com.altera.capstone.bookingvaccine.domain.dao.FamilyDao;
import com.altera.capstone.bookingvaccine.domain.dao.SessionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<BookingDao, Long> {
  // searching with LIKE
  @Query("SELECT b FROM BookingDao b WHERE b.userMapped.firstName LIKE %?1% OR " +
          "b.userMapped.lastName LIKE %?2% OR " +
          "b.familyMapped.fullName LIKE %?3%")
  List<BookingDao> findNameByLike(String firstName, String lastName, String fullName);

  @Query(value = "SELECT * FROM booking b WHERE b.id_user = :id_user AND is_deleted = false ", nativeQuery = true)
  List<BookingDao> findBookingByUserId(@PathVariable("id_user")Long id_user);
}
