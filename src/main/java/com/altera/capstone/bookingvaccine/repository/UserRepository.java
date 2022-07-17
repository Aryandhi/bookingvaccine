package com.altera.capstone.bookingvaccine.repository;

import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.altera.capstone.bookingvaccine.domain.dto.UserDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
  List<UserDao> findByRoles(String roles);

  Page<UserDao> findAllByRoles(String roles, Pageable pageable);

  UserDao getDistinctTopByUsername(String username);
}
