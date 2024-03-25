package com.ashutoshpathak.inventoryservice.service;

import com.ashutoshpathak.inventoryservice.Repository.InventoryRepository;
import com.ashutoshpathak.inventoryservice.model.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer orderQuantity) throws InterruptedException {

        log.info("wait started");
        Thread.sleep(4000);
        log.info("wait ended");

        Optional<Inventory> bySkuCode = inventoryRepository.findBySkuCode(skuCode);
        return bySkuCode.isPresent() && bySkuCode.get().getQuantity() >= orderQuantity;
    }

    public boolean isInStock(String skuCode) throws InterruptedException {

        log.info("wait started");
        Thread.sleep(4000);
        log.info("wait ended");

        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

}
