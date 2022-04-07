package com.junbin.mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junbin.mall.domain.Picture;
import com.junbin.mall.dto.UserProductDto;
import com.junbin.mall.service.UserProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(ProductController.class)
public class UserProductControllerTest {
    @MockBean
    private UserProductService userProductService;
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private UserProductDto userProductDto;
    private Picture picture;

    @BeforeEach
    public void beforeEach() {
        picture = picture.builder()
                .name("football")
                .note("image")
                .url("/usr/img/2.jpg")
                .build();
        List<Picture> pictures = new ArrayList<>();
        pictures.add(picture);

        userProductDto = userProductDto.builder()
                .name("liao")
                .companyName("A")
                .price(14.00)
                .stock(15L)
                .description("a good product")
                .pictures(pictures)
                .build();
    }

    @Nested
    class getProducts {
        @Test
        public void should_return_product_list() throws Exception {
            List<UserProductDto> userProductDtos = new ArrayList<>();
            userProductDtos.add(userProductDto);
            when(userProductService.getProducts()).thenReturn(userProductDtos);

            mockMvc.perform(get("/product"))
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].description", is("a good product")))
                    .andExpect(status().isOk());
            verify(userProductService).getProducts();
        }

        @Test
        public void should_return_a_company_product_list() throws Exception {
            List<UserProductDto> userProductDtos = new ArrayList<>();
            userProductDtos.add(userProductDto);
            String companyName = userProductDto.getCompanyName();
            when(userProductService.getProductsByCompanyName(companyName))
                    .thenReturn(userProductDtos);

            mockMvc.perform(get("/product/"+companyName))
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].companyName", is("A")))
                    .andExpect(status().isOk());
            verify(userProductService).getProductsByCompanyName(companyName);
        }
    }

}
