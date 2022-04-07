package com.junbin.mall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junbin.mall.domain.UserCoupon;
import com.junbin.mall.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty(message = ExceptionMessage.USER_NAME_NOT_EMPTY)
    private String name;

    @NotEmpty(message = ExceptionMessage.USER_PASSWORD_NOT_EMPTY)
    @Size(min=6, message = ExceptionMessage.USER_PASSWORD_NOT_LESS_THAN_SIX)
    private String password;

    @NotEmpty(message = ExceptionMessage.USER_PHONE_NOT_EMPTY)
    @Size(min=11, max=11, message = ExceptionMessage.USER_PHONE_LENGTH_IS_ELEVEN)
    private String phone;

    @NotEmpty(message = ExceptionMessage.USER_ADDRESS_NOT_EMPTY)
    private String address;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<UserCoupon> userCoupons;
}
