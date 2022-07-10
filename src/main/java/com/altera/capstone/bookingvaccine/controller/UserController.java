package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.common.ResponseMessage;
import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.altera.capstone.bookingvaccine.domain.dto.FamilyDto;
import com.altera.capstone.bookingvaccine.domain.dto.SessionDto;
import com.altera.capstone.bookingvaccine.domain.dto.UserDto;
import com.altera.capstone.bookingvaccine.domain.payload.UsernamePassword;
import com.altera.capstone.bookingvaccine.repository.UserRepository;
import com.altera.capstone.bookingvaccine.service.UserService;
import com.altera.capstone.bookingvaccine.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/v1/users")
@Api(tags = "User", value = "User" )
public class UserController {

  @Autowired
  UserService userService;
  @Autowired
  UserRepository userRepository;

//    @GetMapping("/login")
//    public ResponseEntity<?> getLogin(Principal principal){
//        return ResponseEntity.ok(principal.getName()+" Berhasil Login");
//    }

  // GET By ID User for GET Family
//  @ApiOperation(value = "GET User by id for GET All Family",  response = UserDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success GET User by id for GET All Family"),
//  })
//  @GetMapping(value = "/allMember/{id}")
//  public ResponseEntity<Object> getByAllMemberId(@PathVariable(value = "id") Long id_user){
//    return userService.getFamilyByUserId(id_user);
//  }


  // POST
  @ApiOperation(value = "Register user",  response = UsernamePassword.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success register user"),

  })
  @PostMapping("")
  public ResponseEntity<?> register(@RequestBody UserDto req) {
    if(req.getRoles() == null){
      req.setRoles("USER");
      userService.addUser(req);
    } else {
      userService.addUserAdmin(req);
    }
    return ResponseUtil.build(AppConstant.Message.SUCCESS, req, HttpStatus.OK);
  }

  // - Get All User
  @ApiOperation(value = "Get all user",  response = UserDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get list user"),
  })
  @GetMapping(value = "")
  public ResponseEntity<Object> getAll() {
    return userService.getAllUser();
  }

  // GET By ID
  @ApiOperation(value = "GET User by id",  response = UserDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get user by id"),
  })
  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id_user){
    return userService.getUserById(id_user);
  }

  // GET By User role admin
  @ApiOperation(value = "Get By User role admin",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success By User role admin"),

  })
  @GetMapping("/roles/{role}")
  public ResponseEntity<Object> getUserRoleAdmin(@PathVariable("role") String roles){
    return userService.getUserByRoles(roles);
  }

//  // GET By User role user
//  @ApiOperation(value = "Get By User role user",  response = SessionDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success By User role user"),
//
//  })
//  @GetMapping("/role/{user}")
//  public ResponseEntity<Object> getUserRoleUser(@PathVariable("user") String roles){
//    return userService.getUserByRoles(roles);
//  }

  // PUT User By Id
  @ApiOperation(value = "Update user",  response = UserDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success update user"),
  })
  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> updateBook(@PathVariable(value = "id") Long id, @RequestBody UserDto request) {
    return userService.updateUser(id, request);
  }

  // DELETE User By Id
//  @ApiOperation(value = "Delete user",  response = UserDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success delete user"),
//  })
//  @DeleteMapping(value = "/{id}")
//  public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id) {
//    return userService.deleteUser(id);
//  }

}
