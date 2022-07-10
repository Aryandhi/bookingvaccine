package com.altera.capstone.bookingvaccine.repository;

import com.altera.capstone.bookingvaccine.domain.dao.SessionDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altera.capstone.bookingvaccine.domain.dao.NewsVaccineDao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface NewsVaccineRepository extends PagingAndSortingRepository<NewsVaccineDao, Long> {
  // searching with LIKE
  @Query("SELECT n FROM NewsVaccineDao n WHERE n.titleNewsVaccine LIKE %?1% ")
  List<NewsVaccineDao> findTitleNewsByLike(String title);

//  Page<NewsVaccineDao> findAllByOrderByIdDesc(Pageable paging);

//  public Page<NewsVaccineDao> findAllByOrderByIdDesc(@Param(value = "page")int page,
//                                                     @Param(value = "size")int size);
}
