package com.bizpay.service.impl;

import com.bizpay.exceptions.ResourceNotFoundException;
import com.bizpay.mapper.StoreMapper;
import com.bizpay.models.Store;
import com.bizpay.models.StoreContact;
import com.bizpay.models.User;
import com.bizpay.payload.dto.StoreDto;
import com.bizpay.repository.StoreRepository;
import com.bizpay.service.StoreService;
import com.bizpay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserService userService;
    private final StoreMapper storeMapper;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = storeMapper.toEntity(storeDto);
        return storeMapper.toDto(storeRepository.save(store));
    }

    @Override
    public StoreDto getStoreById(Long id) throws ResourceNotFoundException {
        Store store = storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store not found!"));
        return storeMapper.toDto(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(storeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws ResourceNotFoundException {
        User admin =  userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws ResourceNotFoundException {
        User currentUser = userService.getCurrentUser();
        Store existingStore = storeRepository.findByStoreAdminId(currentUser.getId());
        if (existingStore == null){
            throw new ResourceNotFoundException("Store not found!");
        }
        existingStore.setBrand(storeDto.getBrand());
        existingStore.setDescription(storeDto.getDescription());

        if (storeDto.getStoreType() != null){
            existingStore.setStoreType(storeDto.getStoreType());
        }

        if (storeDto.getContact() != null){
            StoreContact storeContact = StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .email(storeDto.getContact().getEmail())
                    .phone(storeDto.getContact().getPhone())
                    .build();
            existingStore.setContact(storeContact);
        }
        Store updatedStore = storeRepository.save(existingStore);
        return storeMapper.toDto(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws ResourceNotFoundException {
        Store store = getStoreByAdmin();
        storeRepository.delete(store);
    }

    @Override
    public StoreDto getStoreByEmployee() throws ResourceNotFoundException {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null){
            throw new ResourceNotFoundException("You don't have permission to access this store!");
        }
        return storeMapper.toDto(currentUser.getStore());
    }
}
