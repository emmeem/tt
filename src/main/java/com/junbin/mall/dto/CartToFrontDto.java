package com.junbin.mall.dto;

import com.junbin.mall.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartToFrontDto {

    private Product product;

    private Integer count;
}
