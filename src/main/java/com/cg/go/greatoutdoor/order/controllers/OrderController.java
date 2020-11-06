package com.cg.go.greatoutdoor.order.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.go.greatoutdoor.order.dto.CreateOrderRequest;
import com.cg.go.greatoutdoor.order.dto.OrderDetails;
import com.cg.go.greatoutdoor.order.dto.UpdateOrderRequest;
import com.cg.go.greatoutdoor.order.entity.OrderEntity;
import com.cg.go.greatoutdoor.order.service.IOrderService;


@RequestMapping("/orderstable")
@RestController

public class OrderController {
	
	@Autowired
	public IOrderService orderService;
	
	/**
     * effective url will be http://localhost:8585/orderstable/add
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public OrderEntity add(@RequestBody CreateOrderRequest requestData) {
    	OrderEntity order = new OrderEntity(requestData.getUserId(),requestData.getTotalPrice(),
        		requestData.getTotalQuantity(),requestData.getDispatchDate(),requestData.getDeliveryDate());
    	order = orderService.addOrder(order);
       // OrderDetails details = toDetails(order);
        return order;
    }
 

	@PutMapping("/update")
    public OrderEntity update(@RequestBody UpdateOrderRequest requestData) {
    	OrderEntity order = new OrderEntity(requestData.getUserId(),requestData.getTotalPrice(),
        		requestData.getTotalQuantity(),requestData.getDispatchDate(),requestData.getDeliveryDate());
		order.setOrderId(requestData.getOrderId());
		order=orderService.updateDate(order.getOrderId(),order.getDispatchDate(),order.getDeliveryDate());
		//OrderDetails details=toDetails(order);
		return order;

    }

    @GetMapping("/allOrders")
    public List<OrderEntity> findAllOrders() {
        List<OrderEntity> order = orderService.findAllOrders();
        //List<OrderDetails> details = toDetails(order);
        return order;
    }
    
    @GetMapping("/OrdersById/{id}")
    public Optional<OrderEntity> findOrdersById(@PathVariable Integer id) {
        return orderService.findOrdersByUserId(id);
    }

	@DeleteMapping("/remove/{id}")
    public String deleteOrder(@PathVariable("id") Integer OrderId) {
        orderService.deleteOrderById(OrderId);
        String response = "removed order with id=" + OrderId;
        return response;
    }
	
	@DeleteMapping("/remove")
	public String deleteAll() {
		orderService.deleteAllOrders();
		return "all orders deleted";
	}
}
