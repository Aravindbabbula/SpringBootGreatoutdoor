
package com.cg.go.greatoutdoor;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.runner.RunWith;
import com.cg.go.greatoutdoor.controllers.OrderController;
import com.cg.go.greatoutdoor.entity.OrderEntity;
import com.cg.go.greatoutdoor.service.OrderServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class  OrderTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private OrderServiceImpl service;
	
	
	@Test
	public void testAddOrderEntity() throws Exception {
		LocalDate dispatchDate = LocalDate.of( 2015 , 6 , 7 );
		LocalDate localDate = LocalDate.of( 2017 , 6 , 7 );
		OrderEntity order = new OrderEntity(520,1000,1,dispatchDate,localDate);

		Mockito.when(service.addOrder(order)).thenReturn(order);
		
		RequestBuilder builder = MockMvcRequestBuilders.post("/add")
				.accept(MediaType.APPLICATION_JSON)
				.content(order.toString())
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(builder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(result.getResponse().getStatus());
	}
	@Test
    public void testfindOrdersByUserId() throws Exception  {
		LocalDate dispatchDate = LocalDate.of( 2015 , 6 , 7 );
		LocalDate localDate = LocalDate.of( 2017 , 6 , 7 );
		Optional<OrderEntity> order = Optional.of(new OrderEntity(520,1000,1,dispatchDate,localDate));
		//OrderEntity order = new OrderEntity(520,1000,1,dispatchDate,localDate);
        Mockito.when(service.findOrdersByUserId(520)).thenReturn(order);
       
        RequestBuilder builder = MockMvcRequestBuilders.get("/OrdersById/520")
                .accept(MediaType.APPLICATION_JSON)
                .content(order.toString())
                .contentType(MediaType.APPLICATION_JSON);
       
        MvcResult result = mvc.perform(builder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        System.out.println(result.getResponse().getStatus());
       
    }
	
	
}