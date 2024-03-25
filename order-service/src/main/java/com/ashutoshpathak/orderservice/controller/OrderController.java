package com.ashutoshpathak.orderservice.controller;

import com.ashutoshpathak.orderservice.dto.OrderRequest;
import com.ashutoshpathak.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name="inventory")
    public CompletableFuture<String> createOrder(@RequestBody OrderRequest orderRequest){

        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
        //return CompletableFuture.supplyAsync(() -> "Order placed successfully.");
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){

        return CompletableFuture.supplyAsync(() -> "OOPS! Sth went wrong. Plz try again l8r!");



    }


}
