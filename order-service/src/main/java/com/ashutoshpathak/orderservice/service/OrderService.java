package com.ashutoshpathak.orderservice.service;

import com.ashutoshpathak.orderservice.dto.OrderLineItemsDTO;
import com.ashutoshpathak.orderservice.dto.OrderRequest;
import com.ashutoshpathak.orderservice.model.Order;
import com.ashutoshpathak.orderservice.model.OrderLineItems;
import com.ashutoshpathak.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    WebClient webClient;

    @Autowired
    OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =
                orderRequest
                        .getOrderLineItemsRequestList()
                        .stream()
                        .map(this::mapToOrderLineItems)
                        .toList();


        order.setOrderLineItemsList(orderLineItems);

        //orderRepository.save(order);

        //Call inventory service and place order if product is in stock
//        Boolean inventoryExists = webClient.get()
//                .uri("http://localhost:8082/api/inventory")
//                .retrieve()
//                .bodyToMono(Boolean.class)
//                .block();

        //List<CustomObject> customObjects = Arrays.asList(/* your list of CustomObjects */);

        List<OrderLineItemsDTO> orderLineItemsList = orderRequest.getOrderLineItemsRequestList();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8082/api/inventory/check");

        for (OrderLineItemsDTO orderLineItem : orderLineItemsList) {
            uriBuilder.queryParam("skuCode", orderLineItem.getSkuCode());
            uriBuilder.queryParam("quantity", orderLineItem.getQuantity());
        }

        String uriWithParams = uriBuilder.toUriString();

        Boolean inventoryExists = webClient.get()
                .uri(uriWithParams)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(Boolean.TRUE.equals(inventoryExists)) {
            orderRepository.save(order);
        } else{
            throw new IllegalArgumentException("insufficient inventory");
        }
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDTO orderLineItemsDTO) {
        return OrderLineItems.builder()
                .quantity(orderLineItemsDTO.getQuantity())
                .skuCode(orderLineItemsDTO.getSkuCode())
                .build();
    }
}
