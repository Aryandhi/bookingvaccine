package com.altera.capstone.bookingvaccine.repository;

import com.altera.capstone.bookingvaccine.domain.dao.CategoryFacilitiesDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryFacilitiesRepository extends JpaRepository<CategoryFacilitiesDao, Long> {
}
