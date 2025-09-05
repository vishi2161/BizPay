package com.bizpay.service;

import com.bizpay.exceptions.ResourceNotFoundException;
import com.bizpay.models.Store;
import com.bizpay.models.User;
import com.bizpay.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws ResourceNotFoundException;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws ResourceNotFoundException;
    StoreDto updateStore(Long id, StoreDto storeDto) throws ResourceNotFoundException;
    void deleteStore(Long id) throws ResourceNotFoundException;
    StoreDto getStoreByEmployee() throws ResourceNotFoundException;
}
