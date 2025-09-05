package com.bizpay.controller;

import com.bizpay.domain.StoreStatus;
import com.bizpay.exceptions.ResourceNotFoundException;
import com.bizpay.mapper.StoreMapper;
import com.bizpay.models.Store;
import com.bizpay.models.User;
import com.bizpay.payload.dto.StoreDto;
import com.bizpay.payload.response.ApiResponse;
import com.bizpay.service.StoreService;
import com.bizpay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;
    private final StoreMapper storeMapper;

    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto,
                                                @RequestHeader("Authorization") String jwt) throws ResourceNotFoundException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto, user));
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin() throws ResourceNotFoundException {
        return ResponseEntity.ok(storeMapper.toDto(storeService.getStoreByAdmin()));
    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee() throws ResourceNotFoundException {
        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable Long id,
                                                @RequestBody StoreDto storeDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(storeService.updateStore(id, storeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws ResourceNotFoundException {
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted the store with id: " + id);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto> moderateStore(@PathVariable Long id,
                                                  @RequestParam StoreStatus storeStatus,
                                                  @RequestBody StoreDto storeDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(storeService.moderateStore(id, storeStatus));
    }

}
