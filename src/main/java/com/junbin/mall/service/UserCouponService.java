package com.junbin.mall.service;

import com.junbin.mall.domain.Coupon;
import com.junbin.mall.domain.User;
import com.junbin.mall.domain.UserCoupon;
import com.junbin.mall.dto.CouponDto;
import com.junbin.mall.dto.UserCouponDto;
import com.junbin.mall.exception.CouponIsNotExistException;
import com.junbin.mall.exception.ExceptionMessage;
import com.junbin.mall.exception.UserIsNotExistException;
import com.junbin.mall.repository.CouponRepository;
import com.junbin.mall.repository.UserCouponRepository;
import com.junbin.mall.repository.UserRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserCouponService {

    private final UserCouponRepository userCouponRepository;

    private final UserRepository userRepository;

    private final CouponRepository couponRepository;

    public UserCouponService(UserCouponRepository userCouponRepository,
                             UserRepository userRepository, CouponRepository couponRepository) {
        this.userCouponRepository = userCouponRepository;
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
    }

    public List<CouponDto> getUserCoupon(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        List<UserCoupon> userCoupons= userCouponRepository.findAllByUser(user.get());

        return ConvertTool.convertList(userCoupons, CouponDto.class);
    }

    @Transactional
    public UserCouponDto setUserCoupon(UserCouponDto userCouponDto) {
        User user = userRepository.findById(userCouponDto.getUserId())
                .orElseThrow(() -> new UserIsNotExistException(ExceptionMessage.USER_NOT_EXIST));
        Coupon coupon =couponRepository.findById(userCouponDto.getCouponId())
                .orElseThrow(() -> new CouponIsNotExistException(ExceptionMessage.COUPON_IS_NOT_EXIST));

        UserCoupon userCoupon = UserCoupon.builder()
                .coupon(coupon)
                .user(user)
                .build();
        UserCoupon newUserCoupon = userCouponRepository.save(userCoupon);

        UserCouponDto newUserCouponDto = UserCouponDto.builder()
                .userId(newUserCoupon.getUser().getId())
                .couponId(newUserCoupon.getCoupon().getId())
                .build();
        return newUserCouponDto;
    }
}
