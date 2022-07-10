package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.domain.dto.BookingDto;
import com.altera.capstone.bookingvaccine.domain.dto.SessionDto;
import com.altera.capstone.bookingvaccine.service.BookingService;
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
@RequestMapping(value = "/v1/booking", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Booking", value = "Booking" )
public class BookingController {
  @Autowired
  private BookingService bookingService;

  // GET All base on pagination and orderBy desc
  @ApiOperation(value = "Get all booking",  response = BookingDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get list booking"),

  })
  @GetMapping(value = "")
  public ResponseEntity<Object> getAll(@RequestParam(value = "page") int page,
                                       @RequestParam(value = "size")int size) {
    return bookingService.getAllBooking(page, size);
  }

  // GET By Id
  @ApiOperation(value = "Get Booking by id",  response = BookingDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get Booking by id"),

  })
  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id){
    return bookingService.getBookingById(id);
  }

  // GET Booking By UserId
  @ApiOperation(value = "Get booking by User id",  response = BookingDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get booking by User id"),

  })
  @GetMapping(value = "/user/{id}")
  public ResponseEntity<Object> getBookingByUserId(@PathVariable(value = "id") Long id){
    return bookingService.getBookingByUserId(id);
  }

  // GET By Like
  @ApiOperation(value = "Get By LIKE name at Booking",  response = BookingDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success Get By LIKE name at Booking"),

  })
  @GetMapping("/search/{search}")
  public ResponseEntity<Object> getSearch(@PathVariable(value = "search") String search){
    return bookingService.getNameByLike(search);
  }

  // POST
//  @ApiOperation(value = "Add Booking",  response = BookingDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success add booking"),
//
//  })
//  @PostMapping(value = "")
//  public ResponseEntity<Object> addBooking(@RequestBody BookingDto request) {
//    try{
//      return bookingService.addBooking(request);
//    } catch (Exception e) {
//      throw e;
//    }
//  }

  // POST
  @ApiOperation(value = "Add Booking Only User",  response = BookingDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success add booking When Only User"),

  })
  @PostMapping(value = "/user")
  public ResponseEntity<Object> addBookingUser(@RequestBody BookingDto request) {
    try{
      return bookingService.addBookingUser(request);
    } catch (Exception e) {
      throw e;
    }
  }

  // POST
  @ApiOperation(value = "Add Booking User Wit Family",  response = BookingDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success add booking User With Family"),

  })
  @PostMapping(value = "/family")
  public ResponseEntity<Object> addBookingUserWithFamily(@RequestBody BookingDto request) {
    try{
      return bookingService.addBookingUserWithFamily(request);
    } catch (Exception e) {
      throw e;
    }
  }

  // PUT
//  @ApiOperation(value = "Update booking",  response = BookingDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success Update booking"),
//
//  })
//  @PutMapping(value = "/{id}")
//  public ResponseEntity<Object> updateBooking(@PathVariable(value = "id") Long id, @RequestBody BookingDto request) {
//    return bookingService.updateBooking(id, request);
//  }

  // DELETE
//  @ApiOperation(value = "Delete booking",  response = BookingDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success delete booking"),
//
//  })
//  @DeleteMapping(value = "/{id}")
//  public ResponseEntity<Object> deleteBooking(@PathVariable(value = "id") Long id) {
//    return bookingService.deleteBooking(id);
//  }
}
