package com.junbin.mall.controller;

import com.junbin.mall.dto.AdminOrderDto;
import com.junbin.mall.service.AdminOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/order")
@Api(tags = "管理员订单管理接口" )
public class AdminOrderController {
    private final AdminOrderService adminOrderService;

    public AdminOrderController (AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    @GetMapping("/{companyName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取所有订单", notes = "根据不同公司返回该公司所有的订单")
    public List<AdminOrderDto> getOrders(@PathVariable String companyName){
        return adminOrderService.getOrders(companyName);
    }
}
