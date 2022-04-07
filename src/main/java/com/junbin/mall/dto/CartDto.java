package com.junbin.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class CartDto {

    private Long productId;

    private Long userId;

    private Integer count;
}
