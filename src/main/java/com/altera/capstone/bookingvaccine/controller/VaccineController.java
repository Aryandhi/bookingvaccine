package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.domain.dto.VaccineDto;
import com.altera.capstone.bookingvaccine.service.VaccineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/v1/vaccine", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Vaccine", value = "Vaccine" )
public class VaccineController {

  @Autowired
  private VaccineService vaccineService;

  // GET
  @ApiOperation(value = "Get all vaccine",  response = VaccineDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get list vaccine"),
  })
  @GetMapping(value = "")
  public ResponseEntity<Object> getAll() {
    return vaccineService.getAllVaccine();
  }

  // POST
  @ApiOperation(value = "Add new vaccine",  response = VaccineDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success add new vaccine"),
  })
  @PostMapping(value = "")
  public ResponseEntity<Object> addVaccine(@RequestBody VaccineDto request) {
    return vaccineService.addVaccine(request);
  }

  // DELETE
//  @ApiOperation(value = "Delete vaccine",  response = VaccineDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success delete vaccine"),
//  })
//  @DeleteMapping(value = "/{id}")
//  public ResponseEntity<Object> deleteVaccine(@PathVariable(value = "id") Long id) {
//    return vaccineService.deleteVaccine(id);
//  }

  // PUT
  @ApiOperation(value = "Update vaccine",  response = VaccineDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success update vaccine"),
  })
  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> updateVaccine(@PathVariable(value = "id") Long id, @RequestBody VaccineDto request) {
    return vaccineService.updateVaccine(id, request);
  }
}
