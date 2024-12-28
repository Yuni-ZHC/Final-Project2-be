package com.TokoBukuNovel.TokoBukuNovel.detail;

import com.TokoBukuNovel.TokoBukuNovel.model.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.*;

public class AdminDetail implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String email;
    private String password;
    private  String username;
    private String role;



    public AdminDetail(Long id, String email, String password, String username, String role ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }



    public static AdminDetail buildAdmin(Admin admin) {
        return new AdminDetail(
                admin.getId(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getUsername(),
                "ADMIN"
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}