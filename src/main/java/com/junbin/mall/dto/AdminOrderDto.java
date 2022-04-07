package com.junbin.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.junbin.mall.domain.OrderItem;
import com.junbin.mall.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class AdminOrderDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String orderNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private User user;

    private Integer status;

    private Double totalPrice;

    private Double finalPrice;

    private String companyName;

    private List<OrderItem> orderItems;
}
