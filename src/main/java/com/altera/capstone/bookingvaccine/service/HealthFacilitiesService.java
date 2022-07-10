package com.altera.capstone.bookingvaccine.service;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.dao.*;
import com.altera.capstone.bookingvaccine.domain.dto.*;
import com.altera.capstone.bookingvaccine.repository.AreaRepository;
import com.altera.capstone.bookingvaccine.repository.CategoryFacilitiesRepository;
import com.altera.capstone.bookingvaccine.repository.HealthFacilitesRepository;
import com.altera.capstone.bookingvaccine.repository.UserRepository;
import com.altera.capstone.bookingvaccine.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HealthFacilitiesService {
  @Autowired
  private HealthFacilitesRepository healthFacilitesRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CategoryFacilitiesRepository categoryFacilitiesRepository;

  @Autowired
  private AreaRepository areaRepository;

  @Autowired
  private ModelMapper mapper;

  // for admin at feature manage schedule vaccine
  public ResponseEntity<Object> getFacilityByUserId(Long id) {
    log.info("Executing get Facility by user id: {} ", id);
    try {
      List<HealthFacilitiesDao> healthFacilitiesDao = healthFacilitesRepository.findFacilityByUserId(id);
      if(healthFacilitiesDao.isEmpty()) {
        log.info("Facility id: {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "facility not found", HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get Facility by User id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, healthFacilitiesDao, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get Facility by User id. Error: {}", e.getMessage());
      log.trace("Get error when get Facility by User id. ", e);
      throw e;
    }
  }

  // for admin get facility by category
  public ResponseEntity<Object> getFacilityByCategoryId(Long id) {
    log.info("Executing get Facility by category id: {} ", id);
    try {
      List<HealthFacilitiesDao> healthFacilitiesDao = healthFacilitesRepository.findFacilityByCategory(id);
      if(healthFacilitiesDao.isEmpty()) {
        log.info("Facility id: {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "Facility not found", HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get Facility by Category id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, healthFacilitiesDao, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get Facility by Category id. Error: {}", e.getMessage());
      log.trace("Get error when get Facility by Category id. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getAllHealthFacility(){
    log.info("Executing get All Health Facility");
    try {
      List<HealthFacilitiesDao> daoList = healthFacilitesRepository.findAll();
      List<HealthFacilitiesDtoResponse> list = new ArrayList<>();
      for(HealthFacilitiesDao dao : daoList) {
        list.add(HealthFacilitiesDtoResponse.builder()
                .id_health_facilities(dao.getId_health_facilities())
                .healthFacilitiesName(dao.getHealthFacilitiesName())
                .addressHealthFacilities(dao.getAddressHealthFacilities())
                .linkLocation(dao.getLinkLocation())
                .phoneFacilities(dao.getPhoneFacilities())
                // from HealthFacilityDtoResponse
                .area(AreaDto.builder()
                        .id_area(dao.getAreaMapped().getId_area())
                        .areaName(dao.getAreaMapped().getAreaName())
                        .build())
                .categoryFacilities(CategoryFacilitiesDto.builder()
                        .id_category_facilities(dao.getCategoryMapped().getId_category_facilities())
                        .categoryFacilitiesName(dao.getCategoryMapped().getCategoryFacilitiesName())
                        .build())
                .user(UserDto.builder()
                        .username(dao.getUserMapped().getUsername())
                        .firstName(dao.getUserMapped().getFirstName())
                        .lastName(dao.getUserMapped().getLastName())
                        .birthDate(dao.getUserMapped().getBirthDate())
                        .gender(dao.getUserMapped().getGender())
                        .email(dao.getUserMapped().getEmail())
                        .noPhone(dao.getUserMapped().getNoPhone())
                        .build())
                .build());
      }
      return ResponseUtil.build(AppConstant.Message.SUCCESS, list, HttpStatus.OK);
    }catch (Exception e){
      log.error("Happened error when get all health facility. Error: {}", e.getMessage());
      log.trace("Get error when get all health facility. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> addHealthFacility(HealthFaciltiesDto request) {
    log.info("Executing add health facility with request: {}", request);
    try{
      log.info("Get area by id: {}", request.getIdArea());
      Optional<AreaDao> areaDaoOptional = areaRepository.findById(request.getIdArea());
      if (areaDaoOptional.isEmpty()) {
        log.info("Area [{}] not found", request.getIdArea());
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "Please, Make Sure Id Area", HttpStatus.BAD_REQUEST);
      }
      log.info("Get category facility by id: {}", request.getIdCategoryFacilities());
      Optional<CategoryFacilitiesDao> categoryFacilitiesDaoOptional = categoryFacilitiesRepository.findById(request.getIdCategoryFacilities());
      if (categoryFacilitiesDaoOptional.isEmpty()) {
        log.info("Category Facility [{}] not found", request.getIdCategoryFacilities());
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "Please, Make Sure Id Category Facility", HttpStatus.BAD_REQUEST);
      }

      log.info("Get user by id: {}", request.getIdUser());
      Optional<UserDao> userDaoOptional = userRepository.findById(request.getIdUser());
      if (userDaoOptional.isEmpty()) {
        log.info("User [{}] not found", request.getIdUser());
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "Please, Make Sure Id User", HttpStatus.BAD_REQUEST);
      }

      HealthFacilitiesDao healthFacilitiesDao = mapper.map(request, HealthFacilitiesDao.class);
      healthFacilitiesDao.setAreaMapped(areaDaoOptional.get());
      healthFacilitiesDao.setCategoryMapped(categoryFacilitiesDaoOptional.get());
      healthFacilitiesDao.setUserMapped(userDaoOptional.get());
      healthFacilitiesDao = healthFacilitesRepository.save(healthFacilitiesDao);
      log.info("Executing add health facility success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(healthFacilitiesDao, HealthFaciltiesDto.class), HttpStatus.OK);

    } catch (Exception e) {
      log.error("Happened error when add Health Facility. Error: {}", e.getMessage());
      log.trace("Get error when add Health Facility. ", e);
      throw e;   }
  }

  public ResponseEntity<Object> updateHealthFacility(Long id, HealthFaciltiesDto request){
    log.info("Executing update health facility with request: {}", request);
    try {
      log.info("GET health facility by id: {}", request);
      Optional<HealthFacilitiesDao>healthFacilitiesDaoOptional= healthFacilitesRepository.findById(id);
      if(healthFacilitiesDaoOptional.isEmpty()) {
        log.info("health facility {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "id facility not found", HttpStatus.BAD_REQUEST);
      }
      //check id user in db -> bad ways because cost performance code,
      //best practice for update id, refer to session service
      Optional<UserDao> userDaoOptional = userRepository.findById(request.getIdUser());

      healthFacilitiesDaoOptional.ifPresent(res -> {
        res.setHealthFacilitiesName(request.getHealthFacilitiesName());
        res.setAddressHealthFacilities(request.getAddressHealthFacilities());
        res.setPhoneFacilities(request.getPhoneFacilities());
        res.setLinkLocation(request.getLinkLocation());
        res.setUserMapped(userDaoOptional.get());
        healthFacilitesRepository.save(res);
      });
      log.info("Executing update health facility success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(healthFacilitiesDaoOptional, HealthFaciltiesDto.class), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when update family. Error: {}", e.getMessage());
      log.trace("Get error when update family. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> deleteHealthFacility(Long id_health_facilities) {
    log.info("Executing delete health facility id: {}", id_health_facilities);
    try{
      Optional<HealthFacilitiesDao> healthFacilitiesDaoOptional = healthFacilitesRepository.findById(id_health_facilities);
      if(healthFacilitiesDaoOptional.isEmpty()) {
        log.info("health facility {} not found", id_health_facilities);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      healthFacilitesRepository.deleteById(id_health_facilities);
      log.info("Executing delete health facility success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, null, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when delete health facility. Error: {}", e.getMessage());
      log.trace("Get error when delete health facility. ", e);
      throw e;
    }
  }
}
