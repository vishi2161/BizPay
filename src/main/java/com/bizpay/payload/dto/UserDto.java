package com.bizpay.payload.dto;

import com.bizpay.domain.UserRole;
import com.bizpay.models.Store;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private String password;

    private UserRole role;

    private StoreDto storeDto;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastLoginAt;
}
