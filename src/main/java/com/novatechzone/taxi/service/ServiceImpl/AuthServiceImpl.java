package com.novatechzone.taxi.service.ServiceImpl;

import com.novatechzone.taxi.dto.AdminLogInRequestDTO;
import com.novatechzone.taxi.model.AdminUser;
import com.novatechzone.taxi.repository.AdminRepository;
import com.novatechzone.taxi.service.AuthService;
import com.novatechzone.taxi.util.JWTUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    JWTUtil jwtUtil;

    public AuthServiceImpl() {
    }

    public ResponseEntity<?> login(AdminLogInRequestDTO adminLogInRequestDTO) {
        Map<String, String> data = new HashMap();
        if (adminLogInRequestDTO.getUsername().equals("")) {
            data.put("msg", "Username Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        } else if (adminLogInRequestDTO.getPassword().equals("")) {
            data.put("msg", "Password Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        } else {
            Optional<AdminUser> optionalAdminUser = this.adminRepository.findByUsernameAndPassword(adminLogInRequestDTO.getUsername(), adminLogInRequestDTO.getPassword());
            if (optionalAdminUser.isPresent()) {
                data.put("msg", "Invalid Credentials");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
            } else {
                AdminUser adminUser = (AdminUser)optionalAdminUser.get();
                String accessToken = this.jwtUtil.generateAccessToken(adminUser);
                data.put("msg", "Success");
                data.put("accessToken", accessToken);
                return ResponseEntity.status(HttpStatus.OK).body(data);
            }
        }
    }
}
