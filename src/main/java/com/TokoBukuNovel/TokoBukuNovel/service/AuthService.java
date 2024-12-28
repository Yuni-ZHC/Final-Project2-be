package com.TokoBukuNovel.TokoBukuNovel.service;

import com.TokoBukuNovel.TokoBukuNovel.detail.AdminDetail;
import com.TokoBukuNovel.TokoBukuNovel.detail.CustomUserDetails;
import com.TokoBukuNovel.TokoBukuNovel.model.Admin;
import com.TokoBukuNovel.TokoBukuNovel.model.LoginRequest;
import com.TokoBukuNovel.TokoBukuNovel.repository.AdminRepository;
import com.TokoBukuNovel.TokoBukuNovel.securityNew.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService  {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CustomUserDetails customUserDetails;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> adminOptional = adminRepository.findByEmail(username);
        if (adminOptional.isPresent()) {
            return AdminDetail.buildAdmin(adminOptional.get());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }


    public Map<String, Object> authenticate(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        UserDetails userDetails = loadUserByUsername(email);

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Email atau password yang Anda masukkan salah");
        }

        // Generate token after successful authentication
        String token = jwtTokenUtil.generateToken(userDetails);

        Map<String, Object> response = new HashMap<>();
        response.put("data", userDetails);
        response.put("token", token);
        return response;
    }

}