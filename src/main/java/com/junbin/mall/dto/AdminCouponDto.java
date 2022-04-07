package com.junbin.mall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.junbin.mall.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class AdminCouponDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String type;

    private String name;

    @NotNull(message = ExceptionMessage.COUPON_COUNT_NOT_EMPTY)
    private Integer count;

    @NotNull(message = ExceptionMessage.COUPON_AMOUNT_NOT_EMPTY)
    private Integer amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date start_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date end_time;

    private String note;

    private String companyName;
}
