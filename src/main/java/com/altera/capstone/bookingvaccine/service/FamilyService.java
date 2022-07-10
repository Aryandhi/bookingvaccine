package com.altera.capstone.bookingvaccine.service;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.dao.FamilyDao;
import com.altera.capstone.bookingvaccine.domain.dao.HealthFacilitiesDao;
import com.altera.capstone.bookingvaccine.domain.dao.SessionDao;
import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.altera.capstone.bookingvaccine.domain.dto.FamilyDto;
import com.altera.capstone.bookingvaccine.domain.dto.FamilyDtoResponse;
import com.altera.capstone.bookingvaccine.repository.FamilyRepository;
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
public class FamilyService {

  @Autowired
  private FamilyRepository familyRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper mapper;

  // for admin at feature manage member (family)
  public ResponseEntity<Object> getFamilyByUserId(Long id) {
    log.info("Executing get Family by user id: {} ", id);
    try {
      List<FamilyDao> familyDaoList = familyRepository.findFamilyByUserId(id);
      if(familyDaoList.isEmpty()) {
        log.info("Family id: {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "Family not found", HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get Family by User id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, familyDaoList, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get Family by User id. Error: {}", e.getMessage());
      log.trace("Get error when get Family by User id. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getAllFamily(){
    log.info("Executing get All Family");
    try {
      List<FamilyDao> daoList = familyRepository.findAll();
      List<FamilyDtoResponse> list = new ArrayList<>();
      for(FamilyDao dao : daoList) {
        list.add(FamilyDtoResponse.builder()
                .id_family(dao.getId_family())
                .nik(dao.getNik())
                .fullName(dao.getFullName())
                .user(UserDao.builder()
                        .id_user(dao.getUserMapped().getId_user())
                        .username(dao.getUserMapped().getUsername())
                        .firstName(dao.getUserMapped().getFirstName())
                        .lastName(dao.getUserMapped().getLastName())
                        .birthDate(dao.getUserMapped().getBirthDate())
                        .gender(dao.getUserMapped().getGender())
                        .email(dao.getUserMapped().getEmail())
                        .noPhone(dao.getUserMapped().getNoPhone())
                        .roles(dao.getUserMapped().getRoles())
                        .build())
                .build());
      }
      return ResponseUtil.build(AppConstant.Message.SUCCESS, list, HttpStatus.OK);
    }catch (Exception e){
      log.error("Happened error when get all book. Error: {}", e.getMessage());
      log.trace("Get error when get all book. ", e);
      throw e;
    }
  }

  // GET Family By Id
  public ResponseEntity<Object> getFamilyById(Long id) {
    log.info("Executing get family by id: {} ", id);
    try {
      Optional<FamilyDao> familyDao = familyRepository.findById(id);
      if (familyDao.isEmpty()) {
        log.info("family id: {} not found", id);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, "ID FAMILY NOT FOUND !", HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get family by id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, familyDao, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get family by id. Error: {}", e.getMessage());
      log.trace("Get error when get family by id. ", e);
      throw e;
    }
  }

//  public ResponseEntity<Object> getFamilyById(Long id) {return null}

//  public ResponseEntity<Object> getFamilyByFullname(String fullName) {return null}

//  public ResponseEntity<Object> getFamilyByUsername(String username){return null}

  public ResponseEntity<Object> addFamily(FamilyDto request) {
    log.info("Executing add family with request: {}", request);
    try{
      log.info("Get user by id: {}", request.getIdUser());
      Optional<UserDao> userDao = userRepository.findById(request.getIdUser());
      if (userDao.isEmpty()) {
        log.info("User [{}] not found", request.getIdUser());
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      FamilyDao familyDao = mapper.map(request, FamilyDao.class);
      familyDao.setUserMapped(userDao.get());
      familyDao = familyRepository.save(familyDao);
      log.info("Executing add family success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(familyDao, FamilyDto.class), HttpStatus.OK);

    } catch (Exception e) {
      log.error("Happened error when add book. Error: {}", e.getMessage());
      log.trace("Get error when add family. ", e);
      throw e;   }
  }

  public ResponseEntity<Object> updateFamily(Long id_family, FamilyDto request) {
    log.info("Executing update family with request: {}", request);
    try {
      Optional<FamilyDao>familyDaoOptional= familyRepository.findById(id_family);
      if(familyDaoOptional.isEmpty()) {
        log.info("Family {} not found", id_family);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      familyDaoOptional.ifPresent(res -> {
        res.setNik(request.getNik());
        res.setFullName(request.getFullName());
        familyRepository.save(res);
      });
      log.info("Executing update family success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(familyDaoOptional, FamilyDto.class), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when update family. Error: {}", e.getMessage());
      log.trace("Get error when update family. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> deleteFamily(Long id_family) {
    log.info("Executing delete family id: {}", id_family);
    try{
      Optional<FamilyDao> familyDaoOptional =familyRepository.findById(id_family);
      if(familyDaoOptional.isEmpty()) {
        log.info("Family {} not found", id_family);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      familyRepository.deleteById(id_family);
      log.info("Executing delete family success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, null, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when delete family. Error: {}", e.getMessage());
      log.trace("Get error when delete family. ", e);
      throw e;
    }
  }
}
