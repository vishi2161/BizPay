package com.bizpay.payload.dto;

import com.bizpay.domain.StoreStatus;
import com.bizpay.models.StoreContact;
import com.bizpay.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Long id;

    private String brand;

    private User storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact;
}
