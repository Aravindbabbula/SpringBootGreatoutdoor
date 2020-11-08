package com.cg.go.greatoutdoor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.go.greatoutdoor.entity.OrderEntity;
import com.cg.go.greatoutdoor.product.exception.ProductException;
import com.cg.go.greatoutdoor.service.OrderServiceImpl;
import com.cg.go.greatoutdoor.dao.IOrderRepository;
import com.cg.go.greatoutdoor.entity.OrderEntity;
import com.cg.go.greatoutdoor.exception.OrderException;

@Transactional
@Service

public class OrderServiceImpl implements IOrderService {
	private static final Logger Log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	 @Autowired
	    IOrderRepository OrderRepository;
	 // Find the orders based on the user Id in the orders table
	 @Override
	public Optional<OrderEntity>findOrdersByUserId(Integer userId){
        Optional<OrderEntity> list= OrderRepository.findById(userId);
		return list;
	}
	 // Find all the required orders in the order table
	@Override
	public List<OrderEntity> findAllOrders(){
        List<OrderEntity> list= OrderRepository.findAll();
		return list;
	}
	// Add orders using the order entity table
	@Override
	public OrderEntity addOrder(OrderEntity orderEntity) {
		boolean exists=OrderRepository.existsById(orderEntity.getUserId());
        if(exists){
            throw new OrderException("Order already exists for id="+orderEntity.getUserId());
        }  
		OrderEntity Order=OrderRepository.save(orderEntity);
		
		return Order;
	}
	// Deleting all orders in the table 
	@Override
	public void deleteAllOrders() {
		
        OrderRepository.deleteAll();
        
	}
	// Deleting the orders based on the order ID in the table
	@Override
	public void deleteOrderById(Integer orderId){
		Optional<OrderEntity> optional=OrderRepository.findById(orderId);
		if(!optional.isPresent()){
            throw new OrderException("Order not found for id="+orderId);
        }
        OrderRepository.deleteById(orderId);
        
	}
	// Update DispatchDate and arrivalDate based on orderID in the table 
	@Override
	public OrderEntity updateDate(Integer orderId, LocalDate dispatchDate, LocalDate arrivalDate) {
		/*boolean exists= OrderRepository.existsById(OrderEntity.getOrderId());
        if(!exists){
            throw new OrderException("Order does not exists for id="+OrderEntity.getOrderId());
        }*/
		Optional<OrderEntity> optional=OrderRepository.findById(orderId);
		OrderEntity order=optional.get();
		OrderRepository.save(order);
		order.setDispatchDate(dispatchDate);
		order.setDeliveryDate(arrivalDate);
		return order;
	}

}
