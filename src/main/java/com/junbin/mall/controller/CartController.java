package com.junbin.mall.controller;

import com.junbin.mall.dto.CartDto;
import com.junbin.mall.dto.CartToFrontDto;
import com.junbin.mall.service.UserCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
@Api(tags = "用户购物车接口" )
public class CartController {

    private  final UserCartService userCartService;

    public CartController(UserCartService userCartService) {
        this.userCartService = userCartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "添加商品到购物车")
    public CartToFrontDto addProductsToCart(@Valid @RequestBody CartDto cartDto) {
        return userCartService.addProductsToCart(cartDto);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取购物车信息")
    public List<CartToFrontDto> getCartInfo(@PathVariable Long userId) {

        return userCartService.getCartInfo(userId);
    }
}
