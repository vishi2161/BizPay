package com.bizpay.mapper;

import com.bizpay.models.User;
import com.bizpay.payload.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(User savedUser) {
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setEmail(savedUser.getEmail());
        userDto.setRole(savedUser.getRole());
        userDto.setFullName(savedUser.getFullName());
        userDto.setPhone(savedUser.getPhone());
        userDto.setLastLoginAt(savedUser.getLastLoginAt());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());

        return userDto;
    }
}
