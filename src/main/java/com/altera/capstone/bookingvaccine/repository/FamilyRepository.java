package com.altera.capstone.bookingvaccine.repository;

import com.altera.capstone.bookingvaccine.domain.dao.FamilyDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyDao, Long> {

  @Query(value = "SELECT * FROM family f WHERE f.user_id = :user_id AND is_deleted = false", nativeQuery = true)
  List<FamilyDao> findFamilyByUserId(@PathVariable("user_id") Long user_id);

}
