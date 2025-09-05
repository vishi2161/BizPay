package com.bizpay.service;

import com.bizpay.exceptions.ResourceNotFoundException;
import com.bizpay.payload.dto.UserDto;
import com.bizpay.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDto userDto) throws ResourceNotFoundException;
    AuthResponse login(UserDto userDto) throws ResourceNotFoundException;
}
