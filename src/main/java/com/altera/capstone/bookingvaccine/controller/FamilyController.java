package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.domain.dto.FamilyDto;
import com.altera.capstone.bookingvaccine.domain.dto.SessionDto;
import com.altera.capstone.bookingvaccine.domain.dto.UserDto;
import com.altera.capstone.bookingvaccine.service.FamilyService;
//import com.altera.capstone.bookingvaccine.service.RestConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/v1/family", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Family", value = "Family" )
public class FamilyController {

  @Autowired
  private FamilyService familyService;

  // - Get All
  @ApiOperation(value = "Get all family",  response = FamilyDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get list family"),
  })
  @GetMapping(value = "")
  public ResponseEntity<Object> getAll() {
    return familyService.getAllFamily();
  }

  // GET By UserId // Filter for manage family
  @ApiOperation(value = "Get family by User id",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get family by User id"),

  })
  @GetMapping(value = "/user/{id}")
  public ResponseEntity<Object> getFamilyByUserId(@PathVariable(value = "id") Long id){
    return familyService.getFamilyByUserId(id);
  }

  // GET By ID
  @ApiOperation(value = "GET Family by id",  response = FamilyDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get family by id"),
  })
  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> getFamilyById(@PathVariable(value = "id") Long id){
    return familyService.getFamilyById(id);
  }

  // POST Family
  @ApiOperation(value = "Add new family",  response = FamilyDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success add new family"),
  })
  @PostMapping(value = "")
  public ResponseEntity<Object> addFamilyMember(@RequestBody FamilyDto request) {
    try{
      return familyService.addFamily(request);
    } catch (Exception e) {
      throw e;
    }
  }

  // PUT Family By Id
  @ApiOperation(value = "Update family",  response = FamilyDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success update family"),
  })
  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> updateBook(@PathVariable(value = "id") Long id, @RequestBody FamilyDto request) {
    return familyService.updateFamily(id, request);
  }

  // DELETE Family By Id
  @ApiOperation(value = "Delete family member",  response = FamilyDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success delete family member"),
  })
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteFamily(@PathVariable(value = "id") Long id) {
    return familyService.deleteFamily(id);
  }
}
