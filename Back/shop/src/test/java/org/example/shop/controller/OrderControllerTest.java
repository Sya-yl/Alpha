package org.example.shop.controller;

import org.example.shop.entity.Order;
import org.example.shop.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testInsertOrder() throws Exception {
        Order order = new Order();
        order.setId(1L);

        when(orderMapper.insert(any(Order.class))).thenReturn(1);

        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"...\":\"...\"}"))
                .andExpect(status().isOk())
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().equals("true")));

        verify(orderMapper, times(1)).insert(any(Order.class));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order();
        order.setId(1L);

        when(orderMapper.selectById(1)).thenReturn(order);

        mockMvc.perform(get("/order/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(orderMapper, times(1)).selectById(1);
    }

}