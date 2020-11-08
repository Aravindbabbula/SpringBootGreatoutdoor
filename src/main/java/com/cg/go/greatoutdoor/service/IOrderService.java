package com.cg.go.greatoutdoor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cg.go.greatoutdoor.entity.OrderEntity;
public interface IOrderService {

	public List<OrderEntity> findAllOrders();

	public Optional<OrderEntity> findOrdersByUserId(Integer userId);

	public OrderEntity addOrder(OrderEntity orderEntity);

	public void deleteAllOrders();

	public void deleteOrderById(Integer orderId);

	public OrderEntity updateDate(Integer orderId, LocalDate dispatchDate, LocalDate arrivalDate);

	

}
