package com.altera.capstone.bookingvaccine.service;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.dao.CategoryFacilitiesDao;
import com.altera.capstone.bookingvaccine.domain.dao.FamilyDao;
import com.altera.capstone.bookingvaccine.domain.dao.HealthFacilitiesDao;
import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.altera.capstone.bookingvaccine.domain.dto.*;
import com.altera.capstone.bookingvaccine.domain.payload.UsernamePassword;
import com.altera.capstone.bookingvaccine.repository.FamilyRepository;
import com.altera.capstone.bookingvaccine.repository.HealthFacilitesRepository;
import com.altera.capstone.bookingvaccine.repository.UserRepository;
import com.altera.capstone.bookingvaccine.util.ResponseUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FamilyRepository familyRepository;

  @Autowired
  private HealthFacilitesRepository healthFacilitesRepository;

  @Autowired
  private ModelMapper mapper;

  public ResponseEntity<Object> addUserAdmin(UserDto request) {
    log.info("Executing add user admin with request: {}", request);
    try {
      // log.info("Get health facility by id: {}", request.getIdHealthFacilities());
      // Optional<HealthFacilitiesDao> healthFacilitiesDaoOptional =
      // healthFacilitesRepository.findById(request.getIdHealthFacilities());
      // if (healthFacilitiesDaoOptional.isEmpty()) {
      // log.info("Health Facility [{}] not found", request.getIdHealthFacilities());
      // return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null,
      // HttpStatus.BAD_REQUEST);
      // }
      UserDao userDao = UserDao.builder()
              .username(request.getUsername())
              .password(request.getPassword())
              .firstName(request.getFirstName())
              .lastName(request.getLastName())
              .gender(request.getGender())
              .birthDate(request.getBirthDate())
              .email(request.getEmail())
              .noPhone(request.getNoPhone())
              .address(request.getAddress())
              .roles(request.getRoles())
//              .healthFacilitiesMapped(healthFacilitiesDaoOptional.get())
              .build();
      // USER not assigned as ADMIN facility !
      // if (request.getRoles()== "ADMIN"){
      // userDao = userRepository.save(userDao);
      // } else{
      // log.info("Executing add user failed !, because role must ADMIN");
      // }
      userDao = userRepository.save(userDao);
      log.info("Executing add user admin success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(userDao, UserDto.class), HttpStatus.OK);

    } catch (Exception e) {
      log.error("Happened error when add user admin. Error: {}", e.getMessage());
      log.trace("Get error when add user. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> addUser(UserDto request) {
    log.info("Executing add user with request: {}", request);
    try {
      UserDao userDao = UserDao.builder()
          .username(request.getUsername())
          .password(request.getPassword())
          .firstName(request.getFirstName())
          .lastName(request.getLastName())
          .gender(request.getGender())
          .birthDate(request.getBirthDate())
          .email(request.getEmail())
          .noPhone(request.getNoPhone())
          .roles(request.getRoles())
          .build();
      userDao = userRepository.save(userDao);
      log.info("Executing add user admin success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(userDao, UserDto.class), HttpStatus.OK);

    } catch (Exception e) {
      log.error("Happened error when add user admin. Error: {}", e.getMessage());
      log.trace("Get error when add user. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getUserById(Long id_user) {
    log.info("Executing get user by id: {} ", id_user);
    try {
      Optional<UserDao> userDao = userRepository.findById(id_user);
      if (userDao.isEmpty()) {
        log.info("user id: {} not found", id_user);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      log.info("Executing get user by id success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, userDao, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get user by id. Error: {}", e.getMessage());
      log.trace("Get error when get user by id. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getUserByRoles(String roles) {
    log.info("Executing get user by roles admin: {} ", roles);
    try {
      List<UserDao> userDao = userRepository.findByRoles(roles);
      log.info("Executing get user by roles admin success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, userDao, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get user by roles admin. Error: {}", e.getMessage());
      log.trace("Get error when get user by roles admin. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> getAllUser() {
    log.info("Executing get all user.");
    try {
      List<UserDao> daoList = userRepository.findAll();
      List<UserDtoResponse> list = new ArrayList<>();
      for (UserDao dao : daoList) {
        list.add(mapper.map(dao, UserDtoResponse.class));
      }
      return ResponseUtil.build(AppConstant.Message.SUCCESS, list, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when get all user. Error: {}", e.getMessage());
      log.trace("Get error when get all user. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> updateUser(Long id_user, UserDto request) {
    log.info("Executing update user with request: {}", request);
    try {
      Optional<UserDao> userDaoOptional = userRepository.findById(id_user);
      if (userDaoOptional.isEmpty()) {
        log.info("User {} not found", id_user);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      userDaoOptional.ifPresent(res -> {
        res.setUsername(request.getUsername());
        res.setPassword(request.getPassword());
        res.setFirstName(request.getFirstName());
        res.setLastName(request.getLastName());
        res.setGender(request.getGender());
        res.setBirthDate(request.getBirthDate());
        res.setEmail(request.getEmail());
        res.setNoPhone(request.getNoPhone());
        userRepository.save(res);
      });
      log.info("Executing update user success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, mapper.map(userDaoOptional, UserDto.class), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when update user. Error: {}", e.getMessage());
      log.trace("Get error when update user. ", e);
      throw e;
    }
  }

  public ResponseEntity<Object> deleteUser(Long id_user) {
    log.info("Executing delete user id: {}", id_user);
    try {
      Optional<UserDao> userDaoOptional = userRepository.findById(id_user);
      if (userDaoOptional.isEmpty()) {
        log.info("User {} not found", id_user);
        return ResponseUtil.build(AppConstant.Message.NOT_FOUND, null, HttpStatus.BAD_REQUEST);
      }
      userRepository.deleteById(id_user);
      log.info("Executing delete user success");
      return ResponseUtil.build(AppConstant.Message.SUCCESS, null, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Happened error when delete user. Error: {}", e.getMessage());
      log.trace("Get error when delete user. ", e);
      throw e;
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDao user = userRepository.getDistinctTopByUsername(username);
    if (user == null)
      throw new UsernameNotFoundException("Username not found");

    return user;
  }

}
