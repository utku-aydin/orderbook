/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.controller;

import com.mthree.orderbook.entity.Company;
import com.mthree.orderbook.entity.User;
import com.mthree.orderbook.service.CompanyService;
import com.mthree.orderbook.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author utkua
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getBuyOrders() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(users);
    }
}
