package com.junbin.mall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junbin.mall.domain.Picture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class UserProductDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private String companyName;

    private Double price;

    private String description;

    private Long stock;

    private List<Picture> pictures;

}
