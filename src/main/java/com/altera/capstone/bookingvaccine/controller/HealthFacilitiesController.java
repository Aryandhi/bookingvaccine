package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.domain.dto.FamilyDto;
import com.altera.capstone.bookingvaccine.domain.dto.HealthFaciltiesDto;
import com.altera.capstone.bookingvaccine.domain.dto.SessionDto;
import com.altera.capstone.bookingvaccine.service.HealthFacilitiesService;
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
@RequestMapping(value = "/v1/facility", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Facility", value = "Facility" )
public class HealthFacilitiesController {
  @Autowired
  private HealthFacilitiesService healthFacilitiesService;

  // - Get All
  @ApiOperation(value = "Get all health facility",  response = HealthFaciltiesDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get list All health facility"),
  })
  @GetMapping(value = "")
  public ResponseEntity<Object> getAll() {
    return healthFacilitiesService.getAllHealthFacility();
  }

  // GET By UserId // Filter for manage schedule vaccine
  @ApiOperation(value = "Get facility by User id",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get facility by User id"),

  })
  @GetMapping(value = "/user/{id}")
  public ResponseEntity<Object> getByAreaId(@PathVariable(value = "id") Long id){
    return healthFacilitiesService.getFacilityByUserId(id);
  }

  // GET By CateogryId
  @ApiOperation(value = "Get facility by category id",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get facility by Category id"),

  })
  @GetMapping(value = "/category/{id}")
  public ResponseEntity<Object> getByCategoryId(@PathVariable(value = "id") Long id){
    return healthFacilitiesService.getFacilityByCategoryId(id);
  }

  // POST Family
  @ApiOperation(value = "Add new health facility",  response = HealthFaciltiesDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success add new health facility"),
  })
  @PostMapping(value = "")
  public ResponseEntity<Object> addHealthFacility(@RequestBody HealthFaciltiesDto request) {
    try{
      return healthFacilitiesService.addHealthFacility(request);
    } catch (Exception e) {
      throw e;
    }
  }

  // PUT Family By Id
  @ApiOperation(value = "Update health facility",  response = HealthFaciltiesDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success update health facility"),
  })
  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> updateHealthFacility(@PathVariable(value = "id") Long id, @RequestBody HealthFaciltiesDto request) {
    return healthFacilitiesService.updateHealthFacility(id, request);
  }

  // DELETE Family By Id
//  @ApiOperation(value = "Delete health facility",  response = HealthFaciltiesDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success delete health facility"),
//  })
//  @DeleteMapping(value = "/{id}")
//  public ResponseEntity<Object> deleteFamily(@PathVariable(value = "id") Long id) {
//    return healthFacilitiesService.deleteHealthFacility(id);
//  }

}
