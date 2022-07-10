package com.altera.capstone.bookingvaccine.controller;

import com.altera.capstone.bookingvaccine.config.security.AuthService;
import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.payload.Login;
import com.altera.capstone.bookingvaccine.domain.payload.TokenResponse;
import com.altera.capstone.bookingvaccine.domain.payload.UsernamePassword;
import com.altera.capstone.bookingvaccine.service.UserService;
import com.altera.capstone.bookingvaccine.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/v1/auth")
@RequiredArgsConstructor
@Api(tags = "Auth", value = "Auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    // Register
    @ApiOperation(value = "Register user", response = UsernamePassword.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success register user"),
            @ApiResponse(code = 400, message = "Too Young for register")

    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsernamePassword req) {
        LocalDate today = LocalDate.now();
        Period diffYear = Period.between(req.getBirthDate(), today);
        if (diffYear.getYears() >= 18) {
            authService.register(req);
            return ResponseUtil.build(AppConstant.Message.SUCCESS, req, HttpStatus.OK);
        } else {
            return ResponseUtil.build(AppConstant.Message.AGE_IS_NOT_ENough, "Age, must more than 18 years old",
                    HttpStatus.BAD_REQUEST);
        }

    }

    // Login
    @ApiOperation(value = "Login user", response = Login.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success login user"),

    })
    @PostMapping("/login")
    public ResponseEntity<?> token(@RequestBody UsernamePassword req) {
        TokenResponse token = authService.generateToken(req);
        return ResponseUtil.build(AppConstant.Message.SUCCESS, token, HttpStatus.OK);
    }
}