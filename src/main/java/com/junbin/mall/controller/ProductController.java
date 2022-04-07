package com.junbin.mall.controller;

import com.junbin.mall.dto.UserProductDto;
import com.junbin.mall.service.UserProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(tags = "用户商品接口" )
public class ProductController {

    private final UserProductService userProductService;

    public ProductController(UserProductService userProductService) {
        this.userProductService = userProductService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取所有商品")
    public List<UserProductDto> getProducts() {
        return userProductService.getProducts();
    }

    @GetMapping("/{companyName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取特定公司商品")
    public List<UserProductDto> getProductsByCompanyName(@PathVariable String companyName) {
        return userProductService.getProductsByCompanyName(companyName);
    }
}
