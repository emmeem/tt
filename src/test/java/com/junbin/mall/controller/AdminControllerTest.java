package com.junbin.mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junbin.mall.dto.AdminDto;
import com.junbin.mall.dto.AdminLoginDto;
import com.junbin.mall.dto.AdminUserDto;
import com.junbin.mall.dto.CompanyDto;
import com.junbin.mall.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @MockBean
    private AdminService adminService;
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private AdminLoginDto adminLoginDto;
    private AdminUserDto adminUserDto;
    private CompanyDto companyDto;
    private AdminDto adminDto;

    @BeforeEach
    public void beforeEach() {
        adminLoginDto = adminLoginDto.builder()
                .name("liao")
                .password("123456")
                .build();

        adminUserDto = adminUserDto.builder()
                .name("rr")
                .password("123567")
                .phone("18117828787")
                .address("1street")
                .build();

        companyDto = companyDto.builder()
                .name("A")
                .build();

        adminDto = adminDto.builder()
                .name("root")
                .password("123456")
                .companyName("A")
                .build();
    }
    @Nested
    class login{
        @Test
        public void should_return_success_when_admin_name_and_pwd_correct() throws Exception {
            when(adminService.login(adminLoginDto)).thenReturn(adminLoginDto);

            String jsonData = objectMapper.writeValueAsString(adminLoginDto);
            mockMvc.perform(post("/admin/login")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            verify(adminService).login(adminLoginDto);
        }

        @Test
        public void should_throw_exception_when_name_is_empty() throws Exception {
            when(adminService.login(adminLoginDto)).thenReturn(adminLoginDto);

            adminLoginDto.setName("");
            String jsonData = objectMapper.writeValueAsString(adminLoginDto);
            mockMvc.perform(post("/admin/login")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message", is("管理员名字不能为空")));
        }

        @Test
        public void should_throw_exception_when_password_is_empty() throws Exception {
            when(adminService.login(adminLoginDto)).thenReturn(adminLoginDto);

            adminLoginDto.setPassword("");
            String jsonData = objectMapper.writeValueAsString(adminLoginDto);
            mockMvc.perform(post("/admin/login")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message", is("管理员密码不能为空")));
        }
    }

    @Nested
    class register {
        @Test
        public void should_return_created_when_admin_info_is_correct() throws Exception {
            when(adminService.register(adminDto)).thenReturn(adminDto);

            String jsonData = objectMapper.writeValueAsString(adminDto);
            mockMvc.perform(post("/admin/register")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.companyName", is("A")));
            verify(adminService).register(adminDto);
        }
    }

    @Nested
    class userManage {
        @Test
        public void should_return_user_list() throws Exception {
            List<AdminUserDto> adminUserDtos = new ArrayList<>();
            adminUserDtos.add(adminUserDto);
            when(adminService.getUsers()).thenReturn(adminUserDtos);

            mockMvc.perform(get("/admin/users"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].phone", is("18117828787")));

        }

        @Test
        public void should_return_success_when_set_user_tag() throws Exception {
            Long id = 1L;
            String tag = "Electronics enthusiast";
            adminUserDto.setTag(tag);
            when(adminService.setUserTag(1L, tag)).thenReturn(adminUserDto);

            mockMvc.perform(post("/admin/user/"+id+"/"+tag))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.tag", is("Electronics enthusiast")));
        }
    }

    @Nested
    class companyManage {
        @Test
        public void should_return_success_when_company_info_is_correct() throws Exception {
            when(adminService.regCompany(companyDto)).thenReturn(companyDto);

            String jsonData = objectMapper.writeValueAsString(companyDto);

            mockMvc.perform(post("/admin/company")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is("A")));

            verify(adminService).regCompany(companyDto);
        }
    }
}
