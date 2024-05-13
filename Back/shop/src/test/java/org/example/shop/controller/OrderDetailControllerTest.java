package org.example.shop.controller;

import org.example.shop.entity.OrderDetail;
import org.example.shop.mapper.OrderDetailMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderDetailController.class)
class OrderDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderDetailMapper orderDetailMapper;

    private OrderDetail orderDetail;

    @BeforeEach
    void setUp() {
        orderDetail = new OrderDetail();
        orderDetail.setOrderDetailId(1);
        orderDetail.setOrderId(101);
        orderDetail.setProductId(201);
        orderDetail.setQuantity(2);
        orderDetail.setUnitPrice(9.99);

        // 模拟insert方法返回1，表示插入成功
        BDDMockito.given(orderDetailMapper.insert(any(OrderDetail.class))).willReturn(1);
        // 模拟selectById方法返回非空的OrderDetail对象
        BDDMockito.given(orderDetailMapper.selectById(anyInt())).willReturn(orderDetail);
        // 模拟updateById方法返回1，表示更新成功
        BDDMockito.given(orderDetailMapper.updateById(any(OrderDetail.class))).willReturn(1);
        // 模拟deleteById方法返回1，表示删除成功
        BDDMockito.given(orderDetailMapper.deleteById(anyInt())).willReturn(1);
    }

    @Test
    void insertOrderDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orderdetails")
                        .contentType("application/json")
                        .content("{\"orderId\":101,\"productId\":201,\"quantity\":2,\"unitPrice\":9.99}"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderDetailById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orderdetails/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateOrderDetail() throws Exception {
        ArgumentCaptor<OrderDetail> captor = ArgumentCaptor.forClass(OrderDetail.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/orderdetails")
                        .contentType("application/json")
                        .content("{\"orderDetailId\":1,\"orderId\":101,\"productId\":201,\"quantity\":2,\"unitPrice\":9.99}"))
                .andExpect(status().isOk());
        // 验证是否调用了updateById方法，并且传入的参数是正确的
        BDDMockito.verify(orderDetailMapper).updateById(captor.capture());
        OrderDetail updatedOrderDetail = captor.getValue();
        assertTrue(updatedOrderDetail.getOrderDetailId() == orderDetail.getOrderDetailId());
    }

    @Test
    void deleteOrderDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/orderdetails/1"))
                .andExpect(status().isOk());
    }
}