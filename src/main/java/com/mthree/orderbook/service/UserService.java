package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.User;
import java.util.List;

public interface UserService {

    /**
     * Fetches all users from the database
     * @return a list with all users
     */
    List<User> getAllUsers();
    
}
