package com.cg.go.greatoutdoor.order.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.go.greatoutdoor.order.dao.IOrderRepository;
import com.cg.go.greatoutdoor.order.service.OrderServiceImpl;
import com.cg.go.greatoutdoor.order.entity.OrderEntity;

@Transactional
@Service

public class OrderServiceImpl implements IOrderService {
	private static final Logger Log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	 @Autowired
	    IOrderRepository OrderRepository;
	 
	 @Override
	public Optional<OrderEntity>findOrdersByUserId(Integer userId){
        Optional<OrderEntity> list= OrderRepository.findById(userId);
		return list;
	}
	@Override
	public List<OrderEntity> findAllOrders(){
        List<OrderEntity> list= OrderRepository.findAll();
		return list;
	}
	@Override
	public OrderEntity addOrder(OrderEntity orderEntity) {
		
		OrderEntity Order=OrderRepository.save(orderEntity);
		return Order;
	}
	@Override
	public void deleteAllOrders() {
		
        OrderRepository.deleteAll();
        
	}
	@Override
	public void deleteOrderById(Integer orderId){
		//Optional<OrderEntity> optional=daoOrder.findById(orderId);
        OrderRepository.deleteById(orderId);
        
	}
	
	@Override
	public OrderEntity updateDate(Integer orderId, LocalDate dispatchDate, LocalDate arrivalDate) {
		Optional<OrderEntity> optional=OrderRepository.findById(orderId);
		OrderEntity order=optional.get();
		OrderRepository.save(order);
		order.setDispatchDate(dispatchDate);
		order.setDeliveryDate(arrivalDate);
		return order;
	}

}
