package com.junbin.mall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junbin.mall.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class CompanyDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty(message = ExceptionMessage.COMPANY_NAME_IS_EMPTY)
    private String name;
}
