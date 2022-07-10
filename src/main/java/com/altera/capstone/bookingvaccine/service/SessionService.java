package com.altera.capstone.bookingvaccine.service;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.dao.*;
import com.altera.capstone.bookingvaccine.domain.dto.SessionDto;
import com.altera.capstone.bookingvaccine.domain.dto.SessionDtoResponse;
import com.altera.capstone.bookingvaccine.repository.*;
import com.altera.capstone.bookingvaccine.util.FileUploadUtil;
import com.altera.capstone.bookingvaccine.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class SessionService {
  @Autowired
  private SessionRepository sessionRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private VaccineRepository vaccineRepository;

  @Autowired
  private AreaRepository areaRepository;

  @Autowired
  private HealthFacilitesRepository healthFacilitesRepository;

  @Autowired
  private ModelMapper mapper;

  @Value("${booking-api.url}")
  private String apiUrl;

  public ResponseEntity<Object> getAllSession(int page, int size) {
    log.info("Executing get all session.");
    try{
      Pageable paging = PageRequest.of(page, size);
      Page<SessionDao> pageResult = sessionRepository.findAll(paging);
      return ResponseUtil.build(AppConstant.Message.SUCCESS, pageResult.toList(), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get all session. Error: {}", e.getMessage());
      log.trace("Get error when get all session. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getAll() {
    log.info("Executing get all session.");
    try{
      List<SessionDao> sessionDaoList = (List<SessionDao>) sessionRepository.findAll();
      return ResponseUtil.build(AppConstant.Message.SUCCESS, sessionDaoList, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get all session. Error: {}", e.getMessage());
      log.trace("Get error when get all session. ", e);
      throw e;
    }
  }

  // Filter for mobile app
  public ResponseEntity<Object> getSessionByAreaId(Long id) {
    log.info("Executing get session by area id: {} ", id);
    try {
      List<SessionDao> sessionDao = sessionRepository.getSessionByAreaId(id);
      if(sessionDao.isEmpty()) {
        log.info("Area id: {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get session by Area id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, sessionDao, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get session by Area id. Error: {}", e.getMessage());
      log.trace("Get error when get session by Area id. ", e);
      throw e;
    }
  }

  // GET Data with LIKE Sorting
  public ResponseEntity<Object> getFacilityByLike(String search){
    try {
      log.info("Execute get data facility");
      List<SessionDao> sessionDaoList = sessionRepository.findByFacilityLike(search, search);
      return ResponseUtil.build(AppConstant.Message.SUCCESS, sessionDaoList, HttpStatus.OK);
    } catch (Exception e){
      log.error("Happened error when get session by id. Error: {}", e.getMessage());
      log.trace("Get error when get session by id. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getSessionById(Long id) {
    log.info("Executing get session by id: {} ", id);
    try {
      Optional<SessionDao> sessionDao = sessionRepository.findById(id);
      if(sessionDao.isEmpty()) {
        log.info("session id: {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get session by id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, sessionDao, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get session by id. Error: {}", e.getMessage());
      log.trace("Get error when get session by id. ", e);
      throw e;
    }
  }

//  public ResponseEntity<Object> addSession(SessionDto request) {
//    log.info("Executing add session with request: {}", request);
//    try{
//
//      log.info("Get area by id: {}", request.getIdArea());
//      Optional<AreaDao> areaDaoOptional = areaRepository.findById(request.getIdArea());
//      if (areaDaoOptional.isEmpty()) {
//        log.info("area [{}] not found", request.getIdArea());
//        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
//      }
//
//      log.info("Get health facility by id: {}", request.getIdHealthFacilities());
//      Optional<HealthFacilitiesDao> healthFacilitiesDaoOptional = healthFacilitesRepository.findById(request.getIdHealthFacilities());
//      if (healthFacilitiesDaoOptional.isEmpty()) {
//        log.info("health facility [{}] not found", request.getIdHealthFacilities());
//        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
//      }
//
//      log.info("Get vaccine by id: {}", request.getIdVaccine());
//      Optional<VaccineDao> vaccineDaoOptional = vaccineRepository.findById(request.getIdVaccine());
//      if (vaccineDaoOptional.isEmpty()) {
//        log.info("vaccine [{}] not found", request.getIdVaccine());
//        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
//      }
//
//      SessionDao sessionDao = SessionDao.builder()
//              .areaMapped(areaDaoOptional.get())
//              .vaccineMapped(vaccineDaoOptional.get())
//              .healthFacilitiesDaoMapped(healthFacilitiesDaoOptional.get())
//              .stock(request.getStock())
//              .startDate(request.getStartDate())
//              .startTime(request.getStartTime())
////              .lastStock(request.getLastStock())
//              .build();
//      sessionDao = sessionRepository.save(sessionDao);
//      log.info("Executing add session success");
//      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(sessionDao, SessionDto.class), HttpStatus.OK);
//
//    } catch (Exception e) {
//      log.error("Happened error when add session. Error: {}", e.getMessage());
//      log.trace("Get error when add session. ", e);
//      throw e;
//    }
//  }

  public ResponseEntity<Object> addSessionWithPhoto(Long vaccine_id, Long area_id, Long health_facilities_id,
                                                    Integer stock, LocalDate start_date, LocalTime start_time,
                                                    MultipartFile multipartFile) throws IOException {
    log.info("Executing add session with request: {}");
    try{
      Optional<AreaDao> areaDaoOptional = areaRepository.findById(area_id);
      if (areaDaoOptional.isEmpty()) {
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "area_id NOT FOUND", HttpStatus.BAD_REQUEST);
      }
      Optional<HealthFacilitiesDao> healthFacilitiesDaoOptional = healthFacilitesRepository.findById(health_facilities_id);
      if (healthFacilitiesDaoOptional.isEmpty()) {
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "health_facility_id NOT FOUND", HttpStatus.BAD_REQUEST);
      }
      Optional<VaccineDao> vaccineDaoOptional = vaccineRepository.findById(vaccine_id);
      if (vaccineDaoOptional.isEmpty()) {
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "vaccine_id NOT FOUND", HttpStatus.BAD_REQUEST);
      }

      if(multipartFile == null) {
        SessionDao sessionDao = SessionDao.builder()
                .areaMapped(areaDaoOptional.get())
                .vaccineMapped(vaccineDaoOptional.get())
                .healthFacilitiesDaoMapped(healthFacilitiesDaoOptional.get())
                .stock(stock)
                .startDate(start_date)
                .startTime(start_time)
//              .lastStock(request.getLastStock())
                .build();
        sessionDao = sessionRepository.save(sessionDao);
        log.info("Executing add session without photo success");
        return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(sessionDao, SessionDto.class), HttpStatus.OK);
      }

      String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
      long size = multipartFile.getSize();
      String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

      SessionDao sessionDao = SessionDao.builder()
              .areaMapped(areaDaoOptional.get())
              .vaccineMapped(vaccineDaoOptional.get())
              .healthFacilitiesDaoMapped(healthFacilitiesDaoOptional.get())
              .stock(stock)
              .startDate(start_date)
              .startTime(start_time)
              .fileName(fileName)
              .size(size)
              .image(apiUrl + "/images/" + filecode)
//              .lastStock(request.getLastStock())
              .build();
      sessionDao = sessionRepository.save(sessionDao);
      log.info("Executing add session success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(sessionDao, SessionDto.class), HttpStatus.OK);

    } catch (Exception e) {
      log.error("Happened error when add session. Error: {}", e.getMessage());
      log.trace("Get error when add session. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> updateSession(Long id, Long vaccine_id, Integer stock,
                                              LocalDate start_date, LocalTime start_time,
                                              MultipartFile multipartFile) throws IOException{
    log.info("Executing update session with request: {}");
    try {
      Optional<SessionDao> sessionDaoOptional = sessionRepository.findById(id);
      if(sessionDaoOptional.isEmpty()) {
        log.info("session {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
//      String msg = "session {} not found";

      String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
      long size = multipartFile.getSize();
      String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

      sessionDaoOptional.ifPresent(res -> {
        if (multipartFile == null){
          res.setVaccineMapped(new VaccineDao(vaccine_id)); //updated vaccine //buat kondisi dengan tenary
          res.setStartDate(start_date);
          res.setStartTime(start_time);
          res.setStock(stock);
          sessionRepository.save(res);
        }
        res.setVaccineMapped(new VaccineDao(vaccine_id)); //updated vaccine //buat kondisi dengan tenary
        res.setStartDate(start_date);
        res.setStartTime(start_time);
        res.setStock(stock);
        res.setFileName(fileName);
        res.setSize(size);
        res.setImage(apiUrl + "/images/" + filecode);
//        res.setLastStock(request.getLastStock());
        sessionRepository.save(res);
      });
      log.info("Executing update session success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(sessionDaoOptional, SessionDto.class), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when update session. Error: {}", e.getMessage());
      log.trace("Get error when update session. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> deleteSession(Long id) {
    log.info("Executing delete session id: {}", id);
    try{
      Optional<SessionDao> sessionDaoOptional = sessionRepository.findById(id);
      if(sessionDaoOptional.isEmpty()) {
        log.info("session {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "Session not found !", HttpStatus.BAD_REQUEST);
      }
      sessionRepository.deleteById(id);
      log.info("Executing delete session success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, "Session deleted !", HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when delete session. Error: {}", e.getMessage());
      log.trace("Get error when delete session. ", e);
      throw e;
    }
  }
}
