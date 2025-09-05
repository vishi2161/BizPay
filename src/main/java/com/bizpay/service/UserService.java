package com.bizpay.service;

import com.bizpay.exceptions.ResourceNotFoundException;
import com.bizpay.models.User;

import java.util.List;


public interface UserService {
    User getUserFromJwtToken(String token) throws ResourceNotFoundException;
    User getCurrentUser() throws ResourceNotFoundException;
    User getUserByEmail(String email) throws ResourceNotFoundException;
    User getUserById(Long id) throws ResourceNotFoundException;
    List<User> getAllUsers();
}
