/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.User;
import com.mthree.orderbook.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utkua
 */
@Repository
public class UserServiceDB implements UserService {
    
    private final UserRepository userRepository;

    public UserServiceDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
}
