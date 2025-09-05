package com.bizpay.service.impl;

import com.bizpay.config.JwtProvider;
import com.bizpay.exceptions.ResourceNotFoundException;
import com.bizpay.models.User;
import com.bizpay.repository.UserRepository;
import com.bizpay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws ResourceNotFoundException {
        String email = jwtProvider.getEmailFromJwtToken(token);
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new ResourceNotFoundException("Invalid token!");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws ResourceNotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new ResourceNotFoundException("User not found!");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new ResourceNotFoundException("User not found!");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
