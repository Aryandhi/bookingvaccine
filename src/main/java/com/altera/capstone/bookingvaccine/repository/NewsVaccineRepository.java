package com.altera.capstone.bookingvaccine.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

import com.altera.capstone.bookingvaccine.domain.dao.NewsVaccineDao;

import java.util.List;

@Repository
public interface NewsVaccineRepository extends PagingAndSortingRepository<NewsVaccineDao, Long> {
  // searching with LIKE
  @Query("SELECT n FROM NewsVaccineDao n WHERE n.titleNewsVaccine LIKE %?1% ")
  List<NewsVaccineDao> findTitleNewsByLike(String title);

}
