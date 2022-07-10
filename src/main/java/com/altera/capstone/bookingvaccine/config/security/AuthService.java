package com.altera.capstone.bookingvaccine.config.security;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.dao.UserDao;
import com.altera.capstone.bookingvaccine.domain.payload.TokenResponse;
import com.altera.capstone.bookingvaccine.domain.payload.UsernamePassword;
import com.altera.capstone.bookingvaccine.repository.UserRepository;
import com.altera.capstone.bookingvaccine.util.BadRequestException;
import com.altera.capstone.bookingvaccine.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public void validateUsernameAsNIK(@RequestBody UserDao req) throws BadRequestException {
        // validasi input username as NIK harus 13 digit
        // if (req.getUsername().length() != 13)
        // throw new BadRequestException("NIK kamu salah, mohon periksa kembali");
        // validasi register harus angka
        // if (!req.getUsername().matches("[0-9]*"))
        // throw new BadRequestException("NIK harus berupa angka");
    }

    public UserDao register(UsernamePassword req) {
        UserDao user = new UserDao();

        if (req.getRoles() == null) {
            req.setRoles("USER");
        }

        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setGender(req.getGender());
        user.setBirthDate(req.getBirthDate());
        user.setEmail(req.getEmail());
        user.setAddress(req.getAddress());
        user.setNoPhone(req.getNoPhone());
        user.setRoles(req.getRoles());
        userRepository.save(user);
        return user;

    }

    public ResponseEntity<Object> registerNew(UsernamePassword req) {
        UserDao user = new UserDao();
        LocalDate today = LocalDate.now();
        Period diffYear = Period.between(req.getBirthDate(), today);

        if (req.getRoles() == null) {
            req.setRoles("USER");
        }

        if (diffYear.getYears() < 18) {
            return ResponseUtil.build(AppConstant.Message.AGE_IS_NOT_ENough, req, HttpStatus.BAD_REQUEST);
        } else {
            user.setUsername(req.getUsername());
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setFirstName(req.getFirstName());
            user.setLastName(req.getLastName());
            user.setBirthDate(req.getBirthDate());
            user.setGender(req.getGender());
            user.setEmail(req.getEmail());
            user.setNoPhone(req.getNoPhone());
            user.setRoles(req.getRoles());
            userRepository.save(user);
            return ResponseUtil.build(AppConstant.Message.SUCCESS, req, HttpStatus.OK);

        }

    }

    public TokenResponse generateToken(UsernamePassword req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication);
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(jwt);
            return tokenResponse;
        } catch (BadCredentialsException e) {
            log.error("Bad Credential", e);
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public TokenResponse generateToken2(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username, password

                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication);
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(jwt);
            return tokenResponse;

        } catch (BadCredentialsException e) {

            log.error("Bad credential", e);
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);

        }
    }
}
