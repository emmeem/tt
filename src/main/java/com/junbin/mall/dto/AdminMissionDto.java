package com.junbin.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminMissionDto {

    private Long id;

    private String explanation;

    private String tips;

    private String content;

    private String answer;

    private Long couponId;
}
