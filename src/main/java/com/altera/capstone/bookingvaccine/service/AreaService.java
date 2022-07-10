package com.altera.capstone.bookingvaccine.service;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.dao.AreaDao;
import com.altera.capstone.bookingvaccine.domain.dao.CategoryFacilitiesDao;
import com.altera.capstone.bookingvaccine.domain.dto.AreaDto;
import com.altera.capstone.bookingvaccine.domain.dto.CategoryFacilitiesDto;
import com.altera.capstone.bookingvaccine.repository.AreaRepository;
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
public class AreaService {
  @Autowired
  private AreaRepository areaRepository;

  @Autowired
  private ModelMapper mapper;

  public ResponseEntity<Object> getAllArea(){
    log.info("Executing get all area");
    try {
      List<AreaDao> daoList = areaRepository.findAll();
      return ResponseUtil.build(AppConstant.Message.SUCCESS, daoList, HttpStatus.OK);
    }catch (Exception e){
      log.error("Happened error when get all area. Error: {}", e.getMessage());
      log.trace("Get error when get all area. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getAreaById(Long id){
    log.info("Executing get area by id: {} ", id);
    try {
      Optional<AreaDao> areaDaoById = areaRepository.findById(id);
      if(areaDaoById.isEmpty()) {
        log.info("area id: {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get area by id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, areaDaoById, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get area by id. Error: {}", e.getMessage());
      log.trace("Get error when get area by id. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> addArea(AreaDto request) {
    log.info("Executing add area with request: {}", request);
    try{
      AreaDao areaDaoNew = mapper.map(request, AreaDao.class);
      areaDaoNew = areaRepository.save(areaDaoNew);
      log.info("Executing add area success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(areaDaoNew, AreaDto.class), HttpStatus.OK);

    } catch (Exception e) {
      log.error("Happened error when add area. Error: {}", e.getMessage());
      log.trace("Get error when add area. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> updateArea(Long id, AreaDto request) {
    log.info("Executing update area with request: {}", request);
    try {
      Optional<AreaDao> areaDaoUpdate = areaRepository.findById(id);
      if(areaDaoUpdate.isEmpty()) {
        log.info("area {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      areaDaoUpdate.ifPresent(res -> {
        res.setAreaName(request.getAreaName());
        areaRepository.save(res);
      });
      log.info("Executing update area success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(areaDaoUpdate, AreaDto.class), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when update area. Error: {}", e.getMessage());
      log.trace("Get error when update area. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> deleteArea(Long id) {
    log.info("Executing delete area id: {}", id);
    try{
      Optional<AreaDao> areaDaoDelete = areaRepository.findById(id);
      if(areaDaoDelete.isEmpty()) {
        log.info("area {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      areaRepository.deleteById(id);
      log.info("Executing delete area success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, null, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when delete area. Error: {}", e.getMessage());
      log.trace("Get error when delete area. ", e);
      throw e;
    }
  }
}

