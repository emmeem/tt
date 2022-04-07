package com.junbin.mall.controller;

import com.junbin.mall.dto.AdminCouponDto;
import com.junbin.mall.service.AdminCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/coupon")
@Api(tags = "管理员优惠券管理接口")
public class AdminCouponController {
    private final AdminCouponService adminCouponService;

    public AdminCouponController(AdminCouponService adminCouponService) {
        this.adminCouponService = adminCouponService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "新建优惠券", httpMethod = "POST")
    public AdminCouponDto createCoupon(@Valid @RequestBody AdminCouponDto adminCouponDto) {
        return adminCouponService.createCoupon(adminCouponDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "删除优惠券")
    public void deleteCoupon(@PathVariable Long id) {
        adminCouponService.deleteCoupon(id);
    }

    @GetMapping("/{companyName}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取所有优惠券")
    public List<AdminCouponDto> getCoupons(@PathVariable String companyName) {
        return adminCouponService.getCoupons(companyName);
    }

    @DeleteMapping("/userCoupon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "删除用户的优惠券")
    public void deleteUserCoupon(@PathVariable Long id) {
        adminCouponService.deleteUserCoupon(id);
    }
}
