package com.ashutoshpathak.inventoryservice.controller;

import com.ashutoshpathak.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam("skuCode") List<String> skuCodes,
                             @RequestParam("quantity") List<Integer> quantities) {
        // Assuming equal size for skuCodes and quantities
        for (int i = 0; i < skuCodes.size(); i++) {
            String skuCode = skuCodes.get(i);
            Integer quantity = quantities.get(i);
            if (!inventoryService.isInStock(skuCode, quantity)) {
                return false;
            }
        }
        return true;
    }
}
