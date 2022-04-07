package com.junbin.mall.controller;

import com.junbin.mall.dto.MissionDto;
import com.junbin.mall.service.UserMissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(MissionController.class)
public class UserMissionControllerTest {
    @MockBean
    private UserMissionService userMissionService;
    @Autowired
    private MockMvc mockMvc;

    private MissionDto missionDto;

    @BeforeEach
    public void beforeEach(){
        missionDto = missionDto.builder()
                .id(1L)
                .explanation("购物操作")
                .tips("答案为四个字")
                .content("提交订单后要记得?")
                .answer("立即支付")
                .couponId(2L)
                .build();
    }

    @Nested
    class getMission{
        @Test
        public void should_return_Mission_info() throws Exception {
            Long missionId = missionDto.getId();
            when(userMissionService.getMission(missionId)).thenReturn(missionDto);

            mockMvc.perform(get("/mission/"+missionId)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            verify(userMissionService).getMission(missionId);

        }
    }

}
