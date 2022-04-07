package com.junbin.mall.repository;

import com.junbin.mall.domain.Coupon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {
    Optional<Coupon> findCouponByName(String name);

    Optional<Coupon> findCouponByCompanyNameAndType(String CompanyName, String Type);

    List<Coupon> findCouponByCompanyName(String CompanyName);
}
