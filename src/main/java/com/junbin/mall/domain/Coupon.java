package com.junbin.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String name;

    private Integer count;

    private Integer amount;

    private Date start_time;

    private Date end_time;

    private String note;

    private String companyName;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserCoupon> userCoupons;
}
