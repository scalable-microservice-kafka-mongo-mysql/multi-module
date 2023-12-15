package com.ashutoshpathak.inventoryservice.service;

import com.ashutoshpathak.inventoryservice.Repository.InventoryRepository;
import com.ashutoshpathak.inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer orderQuantity){
        Optional<Inventory> bySkuCode = inventoryRepository.findBySkuCode(skuCode);
        return bySkuCode.isPresent() && bySkuCode.get().getQuantity() >= orderQuantity;
    }

    public boolean isInStock(String skuCode){
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

}
