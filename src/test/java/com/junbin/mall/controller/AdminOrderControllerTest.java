package com.junbin.mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junbin.mall.domain.OrderItem;
import com.junbin.mall.domain.Picture;
import com.junbin.mall.domain.Product;
import com.junbin.mall.dto.AdminOrderDto;
import com.junbin.mall.service.AdminOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(AdminOrderController.class)
public class AdminOrderControllerTest {
    @MockBean
    private AdminOrderService adminOrderService;
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private AdminOrderDto adminOrderDto;
    private OrderItem orderItem;
    private Product product;
    private Picture picture;

    @BeforeEach
    public void beforeEach() throws ParseException {
        picture = picture.builder()
                .name("football")
                .note("image")
                .url("/usr/img/2.jpg")
                .build();
        List<Picture> pictures = new ArrayList<>();
        pictures.add(picture);

        product = product.builder()
                .id(1L)
                .name("liao")
                .companyName("A")
                .price(14.00)
                .description("a good product")
                .stock(12L)
                .pictures(pictures)
                .build();

        orderItem = orderItem.builder()
                .product(product)
                .amount(2)
                .build();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        String startTime = "2020-10-29 10:00:12";
        String updateTime = "2020-11-29 10:00:12";
        Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
        Date update_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateTime);

        adminOrderDto = adminOrderDto.builder()
                .orderNumber("No.1")
                .createTime(start_time)
                .updateTime(update_time)
                .status(0)
                .totalPrice(20.0)
                .finalPrice(20.0)
                .companyName("A")
                .orderItems(orderItems)
                .build();
    }

    @Nested
    class getOrders {
        @Test
        public void should_return_order_list() throws Exception {
            List<AdminOrderDto> adminOrderDtos = new ArrayList<>();
            adminOrderDtos.add(adminOrderDto);

            String companyName = adminOrderDto.getCompanyName();
            when(adminOrderService.getOrders(companyName))
                    .thenReturn(adminOrderDtos);

            mockMvc.perform(get("/admin/order/"+companyName))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].companyName", is("A")));
            verify(adminOrderService).getOrders(companyName);
        }
    }

}
