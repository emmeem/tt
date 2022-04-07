package com.junbin.mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junbin.mall.dto.UserDto;
import com.junbin.mall.dto.UserLoginDto;
import com.junbin.mall.service.UserService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private UserLoginDto userLoginDto;
    private UserDto userDto;

    @BeforeEach
    public void beforeEach() {
        userLoginDto = userLoginDto.builder()
                       .name("junbin")
                       .password("123456")
                       .build();

        userDto = userDto.builder()
                  .name("rr")
                  .password("123567")
                  .phone("18117828787")
                  .address("1street")
                  .build();
    }

    @Nested
    class login{
        @Test
        public void should_return_success_when_user_name_and_pwd_correct() throws Exception {
            when(userService.login(userLoginDto)).thenReturn(userLoginDto);

            String jsonData = objectMapper.writeValueAsString(userLoginDto);
            mockMvc.perform(post("/user/login")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            verify(userService).login(userLoginDto);
        }

        @Test
        public void should_throw_exception_when_name_is_empty() throws Exception {
            when(userService.login(userLoginDto)).thenReturn(userLoginDto);

            userLoginDto.setName("");
            String jsonData = objectMapper.writeValueAsString(userLoginDto);
            mockMvc.perform(post("/user/login")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message", is("用户名不能为空")));
        }

        @Test
        public void should_throw_exception_when_password_is_empty() throws Exception {
            when(userService.login(userLoginDto)).thenReturn(userLoginDto);

            userLoginDto.setPassword("");
            String jsonData = objectMapper.writeValueAsString(userLoginDto);
            mockMvc.perform(post("/user/login")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message", is("用户密码不能为空")));
        }
    }

    @Nested
    class register{
        @Test
        public void should_return_created_when_user_info_is_correct() throws Exception {
            when(userService.register(userDto)).thenReturn(userDto);

            String jsonData = objectMapper.writeValueAsString(userDto);
            mockMvc.perform(post("/user/register")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
            verify(userService).register(userDto);
        }

        @Test
        public void should_throw_exception_when_password_less_than_six() throws Exception {
            when(userService.register(userDto)).thenReturn(userDto);

            userDto.setPassword("1234");
            String jsonData = objectMapper.writeValueAsString(userDto);
            mockMvc.perform(post("/user/register")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message", is("密码长度不能小于6")));
        }

        @Test
        public void should_throw_exception_when_phone_length_is_not_eleven() throws Exception {
            when(userService.register(userDto)).thenReturn(userDto);

            userDto.setPhone("18118239");
            String jsonData = objectMapper.writeValueAsString(userDto);
            mockMvc.perform(post("/user/register")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message", is("联系方式长度为11")));
        }

        @Test
        public void should_throw_exception_when_address_is_empty() throws Exception {
            when(userService.register(userDto)).thenReturn(userDto);

            userDto.setAddress("");
            String jsonData = objectMapper.writeValueAsString(userDto);
            mockMvc.perform(post("/user/register")
                    .content(jsonData)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message", is("用户地址不能为空")));
        }
    }

    @Nested
    class getUserInfo{
        @Test
        public void should_return_user_info_when_user_is_exist() throws Exception {
            String name = userDto.getName();
            when(userService.getUser(name)).thenReturn(userDto);

            mockMvc.perform(get("/user/"+name))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is("rr")))
                    .andExpect(jsonPath("$.phone", is("18117828787")));

        }
    }
}
