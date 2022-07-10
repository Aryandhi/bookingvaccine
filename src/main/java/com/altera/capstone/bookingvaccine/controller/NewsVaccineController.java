package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.domain.dao.NewsVaccineDao;
import com.altera.capstone.bookingvaccine.domain.dto.SessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.altera.capstone.bookingvaccine.domain.dto.NewsVaccineDto;
import com.altera.capstone.bookingvaccine.service.NewsVaccineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/v1/news", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "NewsVaccine", value = "NewsVaccine")
public class NewsVaccineController {

    @Autowired
    private NewsVaccineService newsVaccineService;

    // GET ALL
    @ApiOperation(value = "Get all news vaccine", response = NewsVaccineDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success get list news vaccine"),
    })
    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<Object> getAllNews(@PathVariable (value = "page")int page,
                                             @PathVariable(value = "size") int size) {
        return newsVaccineService.getAllNewsVaccine(page, size);
    }

    // GET ALL News Data By Desc
    @ApiOperation(value = "Get all news vaccine By Desc", response = NewsVaccineDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success get list news vaccine By Desc"),
    })
    @GetMapping(value = "/desc")
    public Iterable<NewsVaccineDao> getAllByDesc() {
        return newsVaccineService.getAllNewByDesc();
    }

    // GET By Like
    @ApiOperation(value = "Get By LIKE News Vaccine",  response = SessionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success get By LIKE News Vaccine"),

    })
    @GetMapping("/search/{search}")
    public ResponseEntity<Object> getSearch(@PathVariable(value = "search") String search){
        return newsVaccineService.getNewsByLike(search);
    }

    @ApiOperation(value = "Get news vaccine by id", response = NewsVaccineDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success get news vaccine by id"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getNewsById(@PathVariable Long id) {
        return newsVaccineService.getNewsVaccineById(id);
    }

//    @ApiOperation(value = "Add new news vaccine", response = NewsVaccineDto.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success add new news vaccine"),
//    })
//    @PostMapping(value = "")
//    public ResponseEntity<Object> addNews(@RequestBody NewsVaccineDto request) {
//        return newsVaccineService.addNewsVaccine(request);
//    }

    // POST News With Photo
    @ApiOperation(value = "Add new news vaccine with photo", response = NewsVaccineDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success add new news vaccine"),
    })
    @PostMapping(value = "")
    public ResponseEntity<Object> addNewsWithPhoto(@RequestParam(value = "titleNewsVaccine") String titleNewsVaccine,
                                                   @RequestParam(value = "authorNewsVaccine")String authorNewsVaccine,
                                                   @RequestParam(value = "contentNewsVaccine")String contentNewsVaccine,
                                                   @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws IOException {
        return newsVaccineService.addNewsWithPhoto(titleNewsVaccine, authorNewsVaccine, contentNewsVaccine, multipartFile );
    }

    // PUT
//    @ApiOperation(value = "Update news vaccine", response = NewsVaccineDto.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success update news vaccine"),
//    })
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Object> updateNews(@PathVariable(value = "id") Long id,
//            @RequestBody NewsVaccineDto request) {
//        return newsVaccineService.updateNewsVaccinewithPhoto(id, request);
//    }

    // PUT wit photo
    @ApiOperation(value = "Update news vaccine with photo", response = NewsVaccineDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success update news vaccine with photo"),
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateNews(@PathVariable(value = "id") Long id,
                                             @RequestParam(value = "titleNewsVaccine") String titleNewsVaccine,
                                             @RequestParam(value = "authorNewsVaccine")String authorNewsVaccine,
                                             @RequestParam(value = "contentNewsVaccine")String contentNewsVaccine,
                                             @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws IOException {
        return newsVaccineService.updateNewsVaccinewsithPhoto(id, titleNewsVaccine, authorNewsVaccine, contentNewsVaccine, multipartFile );
    }

    // DELETE
    @ApiOperation(value = "Delete news vaccine", response = NewsVaccineDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success delete news vaccine"),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteNews(@PathVariable(value = "id") Long id) {
        return newsVaccineService.deleteNewsVaccine(id);
    }

}
