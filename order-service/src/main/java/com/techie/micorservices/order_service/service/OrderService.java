package com.techie.micorservices.order_service.service;

import com.techie.micorservices.order_service.client.InventoryClient;
import com.techie.micorservices.order_service.model.Order;
import com.techie.micorservices.order_service.records.OrderRequest;
import com.techie.micorservices.order_service.repo.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepo orderRepo, InventoryClient inventoryClient) {
        this.orderRepo = orderRepo;
        this.inventoryClient = inventoryClient;
    }

    public void placeOrder(OrderRequest orderRequest){

        boolean inStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(inStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepo.save(order);
        }else{
            throw new RuntimeException("Product with SkuCode "+ orderRequest.skuCode()+" is not in stock");
        }
    }
}
