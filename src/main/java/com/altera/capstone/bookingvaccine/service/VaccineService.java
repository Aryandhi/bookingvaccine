package com.altera.capstone.bookingvaccine.service;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.dao.VaccineDao;
import com.altera.capstone.bookingvaccine.domain.dto.VaccineDto;
import com.altera.capstone.bookingvaccine.repository.VaccineRepository;
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
public class VaccineService {

  @Autowired
  private VaccineRepository vaccineRepository;

  @Autowired
  private ModelMapper mapper;

  public ResponseEntity<Object> getAllVaccine(){
    log.info("Executing get all vaccine.");
    try{
      List<VaccineDao> daoList = vaccineRepository.findAll();
      return ResponseUtil.build(AppConstant.Message.SUCCESS, daoList, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get all vaccine. Error: {}", e.getMessage());
      log.trace("Get error when get all vaccine. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getByIdVaccine(Long id){
    log.info("Executing get vaccine by id: {} ", id);
    try {
      Optional<VaccineDao> vaccineDao = vaccineRepository.findById(id);
      if(vaccineDao.isEmpty()) {
        log.info("vaccine id: {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get vaccine by id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, vaccineDao, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get vaccine by id. Error: {}", e.getMessage());
      log.trace("Get error when get vaccine by id. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> addVaccine(VaccineDto request){
    log.info("Executing add vaccine with request: {}", request);
    try{
      VaccineDao vaccineDao = mapper.map(request, VaccineDao.class);
      vaccineDao = vaccineRepository.save(vaccineDao);
      log.info("Executing add vaccine success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(vaccineDao, VaccineDto.class), HttpStatus.OK);

    } catch (Exception e) {
      log.error("Happened error when add vaccine. Error: {}", e.getMessage());
      log.trace("Get error when add vaccine. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> updateVaccine(Long id, VaccineDto request){
    log.info("Executing update vaccine with request: {}", request);
    try {
      Optional<VaccineDao> vaccineDao = vaccineRepository.findById(id);
      if(vaccineDao.isEmpty()) {
        log.info("vaccine {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      vaccineDao.ifPresent(res -> {
        res.setVaccineName(request.getVaccineName());
//        res.setStockVaccine(request.getStockVaccine());
        vaccineRepository.save(res);
      });
      log.info("Executing update vaccine success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(vaccineDao, VaccineDto.class), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when update vaccine. Error: {}", e.getMessage());
      log.trace("Get error when update vaccine. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> deleteVaccine(Long id){
    log.info("Executing delete vaccine id: {}", id);
    try{
      Optional<VaccineDao> vaccineDao = vaccineRepository.findById(id);
      if(vaccineDao.isEmpty()) {
        log.info("category {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      vaccineRepository.deleteById(id);
      log.info("Executing delete vaccine success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, null, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when delete vaccine. Error: {}", e.getMessage());
      log.trace("Get error when delete vaccine. ", e);
      throw e;
    }
  }
}
