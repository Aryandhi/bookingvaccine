package com.altera.capstone.bookingvaccine.repository;

import com.altera.capstone.bookingvaccine.domain.dao.FamilyDao;
import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.altera.capstone.bookingvaccine.domain.dto.UserDtoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
  List<UserDao> findByRoles(String roles);

  UserDao getDistinctTopByUsername(String username);
}
