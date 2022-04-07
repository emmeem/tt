package com.junbin.mall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junbin.mall.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class AdminDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty(message = ExceptionMessage.ADMIN_NAME_NOT_EMPTY)
    private String name;

    @NotEmpty(message = ExceptionMessage.ADMIN_PASSWORD_NOT_EMPTY)
    @Size(min=6, message = ExceptionMessage.ADMIN_PASSWORD_NOT_LESS_THAN_SIX)
    private String password;

    @NotEmpty(message = ExceptionMessage.COMPANY_NAME_IS_EMPTY)
    private String companyName;
}
