package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.domain.dto.SessionDto;
import com.altera.capstone.bookingvaccine.domain.dto.UserDto;

import com.altera.capstone.bookingvaccine.repository.UserRepository;
import com.altera.capstone.bookingvaccine.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/v1/users")
@Api(tags = "User", value = "User")
public class UserController {

  @Autowired
  UserService userService;
  @Autowired
  UserRepository userRepository;

  // - Get All User
  @ApiOperation(value = "Get all user", response = UserDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success get list user"),
  })
  @GetMapping(value = "")
  public ResponseEntity<Object> getAll() {
    return userService.getAllUser();
  }

  // GET By ID
  @ApiOperation(value = "GET User by id", response = UserDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success get user by id"),
  })
  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id_user) {
    return userService.getUserById(id_user);
  }

  // GET User By role
  @ApiOperation(value = "Get By User role admin", response = SessionDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success By User role admin"),

  })
  @GetMapping("/roles/{role}")
  public ResponseEntity<Object> getUserRoleAdmin(@PathVariable("role") String roles) {
    return userService.getUserByRoles(roles);
  }

  // PUT User By Id
  @ApiOperation(value = "Update user", response = UserDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success update user"),
  })
  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> updateBook(@PathVariable(value = "id") Long id, @RequestBody UserDto request) {
    return userService.updateUser(id, request);
  }
  // Get All role pagination
  @ApiOperation(value = "Pagination User", response = UserDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success pagination user")
  })
  @GetMapping("/pagination/{roles}")
  public ResponseEntity<Object> getUserByRolesPageable(
          @PathVariable String roles,
          @RequestParam(value = "page") int page,
          @RequestParam(value = "size") int size) {
    return userService.getUserByRolesPageable(roles, page, size);
  }
}
