package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.domain.dto.AreaDto;
import com.altera.capstone.bookingvaccine.domain.dto.SessionDto;
import com.altera.capstone.bookingvaccine.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/v1/session", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Session", value = "Session" )
public class SessionController {
  @Autowired
  private SessionService sessionService;

//  Sort, fulltext search
  // GET All with Pageable
  @ApiOperation(value = "Get all session",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get list session"),

  })
  @GetMapping(value = "/{page}/{size}")
  public ResponseEntity<Object> getAll(@PathVariable(value = "page") int page,
                                       @PathVariable(value = "size") int size) {
    return sessionService.getAllSession(page, size);
  }

  // GET ALL
  @ApiOperation(value = "Get all Session",  response = AreaDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get list Session"),
  })
  @GetMapping(value = "")
  public ResponseEntity<Object> getAll() {
    return sessionService.getAll();
  }

  // GET By Like
  @ApiOperation(value = "Get By LIKE facility OR vaccine",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get By LIKE facility OR vaccine"),

  })
  @GetMapping("/search/{search}")
  public ResponseEntity<Object> getSearch(@PathVariable(value = "search") String search){
    return sessionService.getFacilityByLike(search);
  }

  // GETById
  @ApiOperation(value = "Get session by id",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get session by id"),

  })
  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id){
    return sessionService.getSessionById(id);
  }

  // GET By AreaId // Filter for mobile app
  @ApiOperation(value = "Get session by Area id",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get session by id"),

  })
  @GetMapping(value = "/area/{id}")
  public ResponseEntity<Object> getByAreaId(@PathVariable(value = "id") Long id){
    return sessionService.getSessionByAreaId(id);
  }

  // POST
  @ApiOperation(value = "Add session",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success add session"),

  })
  @PostMapping(value = "")
  public ResponseEntity<Object> addSession(@RequestParam(value = "vaccine_id") Long vaccine_id,
                                           @RequestParam(value = "area_id") Long area_id,
                                           @RequestParam(value = "health_facilities_id") Long health_facilities_id,
                                           @RequestParam(value = "stock") Integer stock,
                                           @RequestParam(value = "start_date")
                                             @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate start_date,
                                           @RequestParam(value = "start_time")
                                             @DateTimeFormat(pattern = "HH:mm") LocalTime start_time,
                                           @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws IOException {

    return sessionService.addSessionWithPhoto(vaccine_id, area_id, health_facilities_id, stock, start_date, start_time, multipartFile);
  }

  // PUT
  @ApiOperation(value = "Update session",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success Update session"),

  })
  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> updateSession(@PathVariable(value = "id") Long id,
                                              @RequestParam(value = "vaccine_id") Long vaccine_id,
                                              @RequestParam(value = "stock") Integer stock,
                                              @RequestParam(value = "start_date")
                                                @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate start_date,
                                              @RequestParam(value = "start_time")
                                                @DateTimeFormat(pattern = "HH:mm") LocalTime start_time,
                                              @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws IOException {
    return sessionService.updateSession(id, vaccine_id, stock, start_date, start_time, multipartFile);
  }

  // DELETE
  @ApiOperation(value = "Delete session",  response = SessionDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success delete session"),

  })
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteSession(@PathVariable(value = "id") Long id) {
    return sessionService.deleteSession(id);
  }
}
