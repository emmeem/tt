package com.junbin.mall.controller;

import com.junbin.mall.dto.CouponDto;
import com.junbin.mall.dto.UserCouponDto;
import com.junbin.mall.service.UserCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/coupon")
@Api(tags = "用户优惠券接口" )
public class CouponController {

    private final UserCouponService userCouponService;

    public CouponController(UserCouponService userCouponService) {
        this.userCouponService = userCouponService;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取优惠劵信息")
    public List<CouponDto> getUserCoupon(@PathVariable Long userId){
       return userCouponService.getUserCoupon(userId);
    }

    @PostMapping("/userCoupon")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="设置用户优惠劵信息")
    public UserCouponDto setUserCoupon(@RequestBody UserCouponDto userCouponDto) {
        return userCouponService.setUserCoupon(userCouponDto);
    }
}
