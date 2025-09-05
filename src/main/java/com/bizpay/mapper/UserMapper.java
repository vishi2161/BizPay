package com.bizpay.mapper;

import com.bizpay.models.User;
import com.bizpay.payload.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
