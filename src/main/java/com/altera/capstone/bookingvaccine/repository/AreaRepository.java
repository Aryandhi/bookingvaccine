package com.altera.capstone.bookingvaccine.repository;

import com.altera.capstone.bookingvaccine.domain.dao.AreaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<AreaDao, Long> {
}
