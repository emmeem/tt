package com.junbin.mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junbin.mall.dto.AdminMissionDto;
import com.junbin.mall.service.AdminMissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(AdminMissionController.class)
public class AdminMissionControllerTest {
    @MockBean
    private AdminMissionService adminMissionService;
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private AdminMissionDto adminMissionDto;

    @BeforeEach
    public void beforeEach(){
        adminMissionDto = adminMissionDto.builder()
                .id(1L)
                .explanation("购物操作")
                .tips("答案为四个字")
                .content("提交订单后要记得？")
                .answer("立即支付")
                .couponId(2L)
                .build();
    }

    @Nested
    class setMission {
        @Test
        public void should_return_created_when_mission_info_is_correct() throws Exception {
            when(adminMissionService.setMission(adminMissionDto)).thenReturn(adminMissionDto);

            String jsonData = objectMapper.writeValueAsString(adminMissionDto);
            mockMvc.perform(post("/admin/mission")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.answer", is("立即支付")));
            verify(adminMissionService).setMission(adminMissionDto);
        }
    }


}
