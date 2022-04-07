package com.junbin.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.junbin.mall.domain.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Coupon coupon;
}
