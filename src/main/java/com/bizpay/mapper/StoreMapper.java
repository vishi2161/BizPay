package com.bizpay.mapper;

import com.bizpay.models.Store;
import com.bizpay.payload.dto.StoreDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StoreMapper {

    // Instance for direct use (optional, if not using Spring injection)
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    StoreDto toDto(Store store);

    Store toEntity(StoreDto storeDto);

}
