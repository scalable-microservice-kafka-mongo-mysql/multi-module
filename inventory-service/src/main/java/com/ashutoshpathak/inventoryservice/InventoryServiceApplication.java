package com.ashutoshpathak.inventoryservice;

import com.ashutoshpathak.inventoryservice.Repository.InventoryRepository;
import com.ashutoshpathak.inventoryservice.model.Inventory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {

			Inventory iphone13 = new Inventory();
			iphone13.setSkuCode("iphone_13");
			iphone13.setQuantity(100);

			Inventory iphone12 = new Inventory();
			iphone12.setSkuCode("iphone_12");
			iphone12.setQuantity(0);

			Inventory ring = new Inventory();
			iphone12.setSkuCode("ring");
			iphone12.setQuantity(69);

			inventoryRepository.save(iphone13);
			inventoryRepository.save(iphone12);
			inventoryRepository.save(ring);


		};
	}

}
