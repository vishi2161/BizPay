package com.bizpay.mapper;

import com.bizpay.models.User;
import com.bizpay.payload.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StoreMapper.class})
public interface UserMapper {

    @Mapping(target = "storeDto", source = "store")
    @Mapping(target = "password", ignore = true)
    UserDto toDto(User user);

    @Mapping(target = "store", source = "storeDto")
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);
}
