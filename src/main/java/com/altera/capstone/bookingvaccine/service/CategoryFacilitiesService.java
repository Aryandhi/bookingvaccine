package com.altera.capstone.bookingvaccine.service;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.dao.CategoryFacilitiesDao;
import com.altera.capstone.bookingvaccine.domain.dto.CategoryFacilitiesDto;
import com.altera.capstone.bookingvaccine.repository.CategoryFacilitiesRepository;
import com.altera.capstone.bookingvaccine.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryFacilitiesService {
  @Autowired
  private CategoryFacilitiesRepository categoryFacilitiesRepository;

  @Autowired
  private ModelMapper mapper;

  public ResponseEntity<Object> getAllCategoryFacility(){
    log.info("Executing get all category facility");
    try {
      List<CategoryFacilitiesDao> daoList = categoryFacilitiesRepository.findAll();
      return ResponseUtil.build(AppConstant.Message.SUCCESS, daoList, HttpStatus.OK);
    }catch (Exception e){
      log.error("Happened error when get all category facility. Error: {}", e.getMessage());
      log.trace("Get error when get all category facility. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getCategoryFacilityById(Long id) {
    log.info("Executing get category facility by id: {} ", id);
    try {
      Optional<CategoryFacilitiesDao> categoryFacilitiesDao = categoryFacilitiesRepository.findById(id);
      if(categoryFacilitiesDao.isEmpty()) {
        log.info("category facility id: {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get category facility by id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, categoryFacilitiesDao, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get category facility by id. Error: {}", e.getMessage());
      log.trace("Get error when get category facility by id. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> addCategoryFacility(CategoryFacilitiesDto request) {
    log.info("Executing add category facility with request: {}", request);
    try{
      CategoryFacilitiesDao categoryFacilitiesDao = mapper.map(request, CategoryFacilitiesDao.class);
      categoryFacilitiesDao = categoryFacilitiesRepository.save(categoryFacilitiesDao);
      log.info("Executing add category facility success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(categoryFacilitiesDao, CategoryFacilitiesDto.class), HttpStatus.OK);

    } catch (Exception e) {
      log.error("Happened error when add category facility. Error: {}", e.getMessage());
      log.trace("Get error when add category facility. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> updateCategoryFacility(Long id, CategoryFacilitiesDto request) {
    log.info("Executing update category facility with request: {}", request);
    try {
      Optional<CategoryFacilitiesDao> categoryFacilitiesDao = categoryFacilitiesRepository.findById(id);
      if(categoryFacilitiesDao.isEmpty()) {
        log.info("category facility {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      categoryFacilitiesDao.ifPresent(res -> {
        res.setCategoryFacilitiesName(request.getCategoryFacilitiesName());
        categoryFacilitiesRepository.save(res);
      });
      log.info("Executing update category facility success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(categoryFacilitiesDao, CategoryFacilitiesDto.class), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when update category facility. Error: {}", e.getMessage());
      log.trace("Get error when update category facility. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> deleteCategoryFacility(Long id) {
    log.info("Executing delete category facility id: {}", id);
    try{
      Optional<CategoryFacilitiesDao> categoryFacilitiesDao = categoryFacilitiesRepository.findById(id);
      if(categoryFacilitiesDao.isEmpty()) {
        log.info("category facility {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      categoryFacilitiesRepository.deleteById(id);
      log.info("Executing delete category facility success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, null, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when delete category facility. Error: {}", e.getMessage());
      log.trace("Get error when delete category facility. ", e);
      throw e;
    }
  }
}
