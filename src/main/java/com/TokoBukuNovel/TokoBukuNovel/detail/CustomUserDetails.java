package com.TokoBukuNovel.TokoBukuNovel.detail;

import com.TokoBukuNovel.TokoBukuNovel.model.Admin;
import com.TokoBukuNovel.TokoBukuNovel.repository.AdminRepository;
import com.TokoBukuNovel.TokoBukuNovel.securityNew.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetails  implements UserDetailsService {


    @Autowired
    AdminRepository adminRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            return AdminDetail.buildAdmin(admin);
        }

        throw new UsernameNotFoundException("Admin Not Found with username: " + username);
    }


}