package com.junbin.mall.repository;

import com.junbin.mall.domain.User;
import com.junbin.mall.domain.UserCoupon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserCouponRepository extends CrudRepository<UserCoupon, Long> {

    void deleteById(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from user_coupon where coupon_id = ?1", nativeQuery = true)
    void deleteByCouponId(Long couponId);

    @Query(value="select * from user_coupon where coupon_id = ?1", nativeQuery = true)
    List<UserCoupon> findAllByCouponId(Long id);

    List<UserCoupon> findAllByUser(User user);
}
