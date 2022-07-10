package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.domain.dto.AreaDto;
import com.altera.capstone.bookingvaccine.domain.dto.CategoryFacilitiesDto;
import com.altera.capstone.bookingvaccine.service.AreaService;
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
@RequestMapping(value = "/v1/area", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Area", value = "Area" )
public class AreaController {
  @Autowired
  private AreaService areaService;

  // GET ALL
  @ApiOperation(value = "Get all Area",  response = AreaDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get list Area"),
  })
  @GetMapping(value = "")
  public ResponseEntity<Object> getAll() {
    return areaService.getAllArea();
  }

  // POST
  @ApiOperation(value = "Add new area",  response = AreaDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success add new Area"),
  })
  @PostMapping(value = "")
  public ResponseEntity<Object> addArea(@RequestBody AreaDto request) {
    return areaService.addArea(request);
  }

  // PUT
  @ApiOperation(value = "Update area",  response = AreaDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success update Area"),
  })
  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> updateArea(@PathVariable(value = "id") Long id, @RequestBody AreaDto request) {
    return areaService.updateArea(id, request);
  }

  // DELETE
//  @ApiOperation(value = "Delete area",  response = AreaDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success delete area"),
//  })
//  @DeleteMapping(value = "/{id}")
//  public ResponseEntity<Object> deleteArea(@PathVariable(value = "id") Long id) {
//    return areaService.deleteArea(id);
//  }
}
