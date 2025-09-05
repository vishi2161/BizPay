package com.bizpay.controller;

import com.bizpay.exceptions.ResourceNotFoundException;
import com.bizpay.mapper.UserMapper;
import com.bizpay.models.User;
import com.bizpay.payload.dto.UserDto;
import com.bizpay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws ResourceNotFoundException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id, @RequestHeader("Authorization") String jwt) throws ResourceNotFoundException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
