package com.TokoBukuNovel.TokoBukuNovel.controller;

import com.TokoBukuNovel.TokoBukuNovel.DTO.PasswordDTO;
import com.TokoBukuNovel.TokoBukuNovel.exception.CommonResponse;
import com.TokoBukuNovel.TokoBukuNovel.exception.ResponseHelper;
import com.TokoBukuNovel.TokoBukuNovel.model.Admin;
import com.TokoBukuNovel.TokoBukuNovel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        Admin registeredAdmin = adminService.registerAdmin(admin);
        return new ResponseEntity<>(registeredAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmin() {
        List<Admin> admins = adminService.getAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        Admin updatedAdmin = adminService.edit(id, admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @PutMapping("/edit-password/{id}")
    public CommonResponse<Admin> putPassword(@RequestBody PasswordDTO password, @PathVariable Long id) {
        return ResponseHelper.ok(adminService.putPasswordAdmin(password, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        Map<String, Boolean> response = adminService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}