package com.cg.go.greatoutdoor.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.go.greatoutdoor.entity.OrderEntity;

public interface IOrderRepository extends JpaRepository<OrderEntity,Integer>{
	
	
	
	 //public List<OrderEntity> findOrdersByUserId(Integer userId); //public void
	/* * updateDate(String orderId, LocalDate dispatchDate, LocalDate arrivalDate);
	 */
}
