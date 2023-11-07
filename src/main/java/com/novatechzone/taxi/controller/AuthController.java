package com.novatechzone.taxi.controller;

import com.novatechzone.taxi.dto.AdminLogInRequestDTO;
import com.novatechzone.taxi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth"})
public class AuthController {
    @Autowired
    AuthService authService;

    public AuthController() {
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> login(@RequestBody AdminLogInRequestDTO adminLogInRequestDTO) {
        return this.authService.login(adminLogInRequestDTO);
    }
}
