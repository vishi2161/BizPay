package com.bizpay.service.impl;

import com.bizpay.config.JwtProvider;
import com.bizpay.domain.UserRole;
import com.bizpay.exceptions.ResourceNotFoundException;
import com.bizpay.mapper.UserMapper;
import com.bizpay.models.User;
import com.bizpay.payload.dto.UserDto;
import com.bizpay.payload.response.AuthResponse;
import com.bizpay.repository.UserRepository;
import com.bizpay.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserServiceImpl customUserServiceImpl;
    private final UserMapper userMapper;


    @Override
    public AuthResponse signup(UserDto userDto) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null){
            throw new ResourceNotFoundException("Email id already registered!");
        }
        if (userDto.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new ResourceNotFoundException("Role Admin is not allowed!");
        }
        User newUser = userMapper.toEntity(userDto);

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully!");
        authResponse.setUser(userMapper.toDto(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) throws ResourceNotFoundException {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        Authentication authentication = authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role = authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(email);

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

//        Auth response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successfully!");
        authResponse.setUser(userMapper.toDto(user));

        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws ResourceNotFoundException {

//        compare password
        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(email);

        if (userDetails == null){
            throw new ResourceNotFoundException("Email id doesn't exist " + email);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new ResourceNotFoundException("Password doesn't match.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
