package com.junbin.mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junbin.mall.dto.AdminCouponDto;
import com.junbin.mall.service.AdminCouponService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(AdminCouponController.class)
public class AdminCouponControllerTest {
    @MockBean
    private AdminCouponService adminCouponService;
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private AdminCouponDto adminCouponDto;

    @BeforeEach
    public void beforeEach() throws ParseException {
        String startTime = "2020-10-29 10:00:12";
        String endTime = "2020-11-29 10:00:12";
        Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
        Date end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);

        adminCouponDto =  adminCouponDto.builder()
                .type("1")
                .name("computer")
                .count(34)
                .amount(15)
                .start_time(start_time)
                .end_time(end_time)
                .note("A company coupon")
                .companyName("A")
                .build();
    }

    @Nested
    class create{
        @Test
        public void should_return_created_when_coupon_info_is_correct() throws Exception {
            when(adminCouponService.createCoupon(adminCouponDto)).thenReturn(adminCouponDto);

            String jsonData = objectMapper.writeValueAsString(adminCouponDto);
            mockMvc.perform(post("/admin/coupon")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
            verify(adminCouponService).createCoupon(adminCouponDto);
        }
    }

    @Nested
    class get{
        @Test
        public void should_return_coupon_list() throws Exception {
            List<AdminCouponDto> adminCouponDtos = new ArrayList<>();
            adminCouponDtos.add(adminCouponDto);
            String companyName = adminCouponDto.getCompanyName();
            when(adminCouponService.getCoupons(companyName))
                    .thenReturn(adminCouponDtos);

            mockMvc.perform(get("/admin/coupon/"+companyName))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].companyName", is("A")));
            verify(adminCouponService).getCoupons(companyName);
        }
    }
}
