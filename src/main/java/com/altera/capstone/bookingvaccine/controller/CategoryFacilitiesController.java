package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.domain.dto.CategoryFacilitiesDto;
import com.altera.capstone.bookingvaccine.service.CategoryFacilitiesService;
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
@RequestMapping(value = "/v1/category", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "CategoryFacility", value = "CategoryFacility" )
public class CategoryFacilitiesController {
  @Autowired
  private CategoryFacilitiesService categoryFacilitiesService;

  // GET ALL
  @ApiOperation(value = "Get all category facility",  response = CategoryFacilitiesDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success get list category facility"),
  })
  @GetMapping(value = "")
  public ResponseEntity<Object> getAll() {
    return categoryFacilitiesService.getAllCategoryFacility();
  }

  // POST
  @ApiOperation(value = "Add new category facility",  response = CategoryFacilitiesDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success add new category facility"),
  })
  @PostMapping(value = "")
  public ResponseEntity<Object> addCategory(@RequestBody CategoryFacilitiesDto request) {
    return categoryFacilitiesService.addCategoryFacility(request);
  }

  // PUT
  @ApiOperation(value = "Update category facility",  response = CategoryFacilitiesDto.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Success update category facility"),
  })
  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> updateCategory(@PathVariable(value = "id") Long id, @RequestBody CategoryFacilitiesDto request) {
    return categoryFacilitiesService.updateCategoryFacility(id, request);
  }

  // DELETE
//  @ApiOperation(value = "Delete category facility",  response = CategoryFacilitiesDto.class)
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "Success delete category facility"),
//  })
//  @DeleteMapping(value = "/{id}")
//  public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id") Long id) {
//    return categoryFacilitiesService.deleteCategoryFacility(id);
//  }
}
