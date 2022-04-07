package com.junbin.mall.controller;

import com.junbin.mall.dto.UserDto;
import com.junbin.mall.dto.UserLoginDto;
import com.junbin.mall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "用户登录")
    public UserLoginDto login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "用户注册")
    public UserDto register(@Valid @RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取用户信息")
    public UserDto getUser(@PathVariable String name) {
        return userService.getUser(name);
    }
}
