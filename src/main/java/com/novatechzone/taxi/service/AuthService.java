package com.novatechzone.taxi.service;

import com.novatechzone.taxi.dto.AdminLogInRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<?> login(AdminLogInRequestDTO adminLogInRequestDTO);
}