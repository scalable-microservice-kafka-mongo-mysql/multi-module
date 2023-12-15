package com.ashutoshpathak.orderservice.service;

import com.ashutoshpathak.orderservice.dto.OrderLineItemsDTO;
import com.ashutoshpathak.orderservice.dto.OrderRequest;
import com.ashutoshpathak.orderservice.model.Order;
import com.ashutoshpathak.orderservice.model.OrderLineItems;
import com.ashutoshpathak.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

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

        orderRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDTO orderLineItemsDTO) {
        return OrderLineItems.builder()
                .id(orderLineItemsDTO.getId())
                .price(orderLineItemsDTO.getPrice())
                .quantity(orderLineItemsDTO.getQuantity())
                .skuCode(orderLineItemsDTO.getSkuCode())
                .build();
    }
}
