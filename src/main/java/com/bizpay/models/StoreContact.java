package com.bizpay.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreContact {
    private String address;
    private String phone;
    @Email(message = "Provide valid email!")
    private String email;
}
