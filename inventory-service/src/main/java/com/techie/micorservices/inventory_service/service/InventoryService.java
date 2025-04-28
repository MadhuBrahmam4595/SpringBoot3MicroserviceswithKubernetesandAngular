package com.techie.micorservices.inventory_service.service;

import com.techie.micorservices.inventory_service.repo.InventoryRepo;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepo;

    public InventoryService(InventoryRepo inventoryRepo){
        this.inventoryRepo = inventoryRepo;
    }

    public boolean isInStock(String skuCode, Integer quantity){
        return inventoryRepo.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }


}
