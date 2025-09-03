package com.bizpay.service;

import com.bizpay.exceptions.UserException;
import com.bizpay.payload.dto.UserDto;
import com.bizpay.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;
}
