package com.junbin.mall.controller;

import com.junbin.mall.dto.AdminProductDto;
import com.junbin.mall.service.AdminProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/product")
@Api(tags = "管理员商品管理接口")
public class AdminProductController {
    private final AdminProductService adminProductService;

    public AdminProductController(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "添加商品")
    public AdminProductDto createProduct(@RequestBody AdminProductDto adminProductDto) {
        return adminProductService.createProduct(adminProductDto);
    }

    @GetMapping("/{companyName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取所有商品")
    public List<AdminProductDto> getProducts(@PathVariable String companyName) {
        return adminProductService.getProducts(companyName);
    }


    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "更新商品信息")
    public AdminProductDto update(@PathVariable Long id, @RequestBody AdminProductDto adminProductDto) {
        return adminProductService.update(id, adminProductDto);
    }
}
