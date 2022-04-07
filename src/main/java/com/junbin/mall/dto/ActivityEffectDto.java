package com.junbin.mall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityEffectDto {
    private Long numsOfKnowActivity;

    private Long numsOfJoinActivity;

    private Long numsOfGetCoupon;
}
